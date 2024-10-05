package Vue;

import Modèle.ClassesMétier.Utilisateur;
import Contrôleur.*;

public interface VueLoginWindow
{
    void run();

    void setContrôleur(Contrôleur contrôleur);

    Utilisateur Login();
}
