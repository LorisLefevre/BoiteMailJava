package Vue;

import Modèle.ClassesMétier.Utilisateur;
import Contrôleur.*;
import Vue.InterfacesGraphiques.LoginWindow;

public interface VueLoginWindow
{
    void run();

    void setContrôleur(Contrôleur contrôleur);

    Utilisateur Login();
}
