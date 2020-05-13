package fr.darkxell.galaxia.theory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TheoryAPI {

	public static ArrayList<TheoryPlanet> PLANETS = new ArrayList<TheoryPlanet>(10);

	public static TheoryPlanet getPlanet(String name) {
		for (int i = 0; i < PLANETS.size(); i++) {
			if (name.toLowerCase().startsWith(PLANETS.get(i).name))
				return PLANETS.get(i);
		}
		return null;
	}

	private static long lastrefresh = 0l;

	public static long getLastRefresh() {
		return lastrefresh;
	}

	public static void refreshPlanets() {
		ArrayList<TheoryPlanet> PLANETStmp = new ArrayList<TheoryPlanet>(10);

		// Fetches the planet list
		Document doc = Jsoup.parse(readFromUrl("https://theory.azurewebsites.net/Sirius").toString());
		Elements planetelements = doc.select("section[id=\"main\"]").first().select("div[class=\"box\"]");
		for (int i = 0; i < planetelements.size(); i++) {
			Element e = planetelements.get(i);
			TheoryPlanet planet = new TheoryPlanet(e.selectFirst("a").attr("href"),
					e.select("h3").text().trim().toLowerCase(), e.selectFirst("p").text(), e.select("p").get(1).text());
			PLANETStmp.add(planet);
		}

		// Gathers drop info for listed planets
		for (int i = 0; i < PLANETStmp.size(); i++) {
			TheoryPlanet ptemp = PLANETStmp.get(i);
			Document pdoc = Jsoup.parse(readFromUrl("https://theory.azurewebsites.net" + ptemp.theoryID).toString());
			ptemp.recap = pdoc.select("pre[id=\"recap\"]").first().text().split("\n");
			ptemp.ring = ptemp.recap[0].split("-")[1].split("\\(")[0].trim();
			for (int j = 3; j < ptemp.recap.length; j++) {
				switch (j) {
				case 4:
					ptemp.drop_weak = ptemp.recap[j].trim();
					break;
				case 6:
					ptemp.drop_med = ptemp.recap[j].trim();
					break;
				case 8:
					ptemp.drop_strong = ptemp.recap[j].trim();
					break;
				case 10:
					ptemp.drop_boss = ptemp.recap[j].trim();
					break;
				case 12:
					ptemp.drop_amarna_1 = ptemp.recap[j].trim();
					break;
				case 13:
					ptemp.drop_amarna_2 = ptemp.recap[j].trim();
					break;
				case 14:
					ptemp.drop_amarna_3 = ptemp.recap[j].trim();
					break;
				case 16:
					ptemp.drop_soris_1 = ptemp.recap[j].trim();
					break;
				case 17:
					ptemp.drop_soris_2 = ptemp.recap[j].trim();
					break;
				case 18:
					ptemp.drop_soris_3 = ptemp.recap[j].trim();
					break;
				case 20:
					ptemp.drop_giza_1 = ptemp.recap[j].trim();
					break;
				case 21:
					ptemp.drop_giza_2 = ptemp.recap[j].trim();
					break;
				case 22:
					ptemp.drop_giza_3 = ptemp.recap[j].trim();
					break;
				default:
					break;
				}
			}

		}

		PLANETS = PLANETStmp;
	}

	private static StringBuffer readFromUrl(String Url) {
		StringBuffer sb = new StringBuffer();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(new URL(Url).openStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null)
				sb.append(inputLine + "\n");
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return sb;
	}

}
