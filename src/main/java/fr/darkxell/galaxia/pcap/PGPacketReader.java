package fr.darkxell.galaxia.pcap;

import java.util.List;

import org.pcap4j.core.PacketListener;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;

public class PGPacketReader {

	public PGPacketReader() {

	}

	public void start() {

		try {
			List<PcapNetworkInterface> allDevs = null;
			PcapNetworkInterface device;

			// Gets the device
			allDevs = Pcaps.findAllDevs();
			System.out.println("You chose: " + allDevs.get(0));
			device = allDevs.get(0);

			// Open the device and get a handle
			int snapshotLength = 65536; // in bytes
			int readTimeout = 50; // in milliseconds
			final PcapHandle handle;
			handle = device.openLive(snapshotLength, PromiscuousMode.PROMISCUOUS, readTimeout);

			// Create a listener that defines what to do with the received packets
			PacketListener listener = new PacketListener() {
				@Override
				public void gotPacket(Packet packet) {
					// Override the default gotPacket() function and process packet
					System.out.println(handle.getTimestamp());
					System.out.println(new String(packet.getRawData()));
				}
			};

			// Tell the handle to loop using the listener we created
			try {
				int maxPackets = 50;
				handle.loop(maxPackets, listener);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Cleanup when complete
			handle.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
