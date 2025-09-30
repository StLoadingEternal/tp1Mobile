package Donnee;

import aModels.Activite;
import aModels.Joueur;

public class Data {
    static Joueur[] joueurs = new Joueur[] {
            new Joueur("Léa", "Tremblay", "lea.tremblay@gmail.com", "(514) 555-1234"),
            new Joueur("Émile", "Gagnon", "emile.gagnon@videotron.ca", "(438) 555-5678"),
            new Joueur("Sophie", "Morin", "sophie.morin@hotmail.com", "(418) 555-2468"),
            new Joueur("Nathan", "Smith", "nathan.smith@bell.net", "(613) 555-7890"),
            new Joueur("Olivia", "Lavoie", "olivia.lavoie@icloud.com", "(581) 555-1122"),
            new Joueur("Jacob", "Martin", "jacob.martin@yahoo.ca", "(819) 555-3344"),
            new Joueur("Chloe", "Dubois", "chloe.dubois@outlook.com", "(450) 555-9988"),
            new Joueur("Lucas", "Roy", "lucas.roy@live.ca", "(709) 555-8877"),
            new Joueur("Ava", "Bouchard", "ava.bouchard@gmail.com", "(873) 555-7766"),
            new Joueur("William", "Ouellet", "william.ouellet@protonmail.com", "(236) 555-6655")
    };

    static Activite[] activites = new Activite[] {
            new Activite("2025-09-15 20:00", "Montreal Impact vs Toronto FC", "Montréal", Activite.Stade.DOMICILE, 0),
            new Activite("2025-09-18 19:30", "Toronto Raptors vs Boston Celtics", "Toronto", Activite.Stade.DOMICILE, 0),
            new Activite("2025-09-22 21:00", "Vancouver Whitecaps vs LA Galaxy", "Vancouver", Activite.Stade.DOMICILE, 0),
            new Activite("2025-09-25 20:30", "Montreal Impact vs NYCFC", "Montréal", Activite.Stade.DOMICILE, 0),
            new Activite("2025-09-29 19:45", "Toronto Raptors vs Chicago Bulls", "Toronto", Activite.Stade.DOMICILE, 0),
            new Activite("2025-10-02 18:00", "Vancouver Whitecaps vs Seattle Sounders", "Vancouver", Activite.Stade.EXTERIEUR, 0)
    };

    public static Joueur[] getJoueurs() {
        return joueurs;
    }

    public static Activite[] getActivites() {
        return activites;
    }
}
