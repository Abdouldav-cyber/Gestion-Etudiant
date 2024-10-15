package controller;

import model.Resultat;
import java.util.ArrayList;
import java.util.List;

public class ResultatController {
    private List<Resultat> resultats = new ArrayList<>();

    public void ajouterResultat(Resultat resultat) {
        resultats.add(resultat);
    }

    public void modifierResultat(String id, Resultat nouvelResultat) {
        for (int i = 0; i < resultats.size(); i++) {
            if (resultats.get(i).getId().equals(id)) {
                resultats.set(i, nouvelResultat);
                return; // Sortir après la modification
            }
        }
    }

    public void supprimerResultat(String id) {
        resultats.removeIf(resultat -> resultat.getId().equals(id));
    }

    public List<Resultat> getListeResultats() {
        return resultats;
    }
}