package Vue;

import Modèle.ClassesMétier.*;
import Modèle.CoucheAccèsDonnées.*;
import Contrôleur.*;
import Vue.InterfacesGraphiques.MailWindow;
public interface VueMailWindow
{
    void run();

    void setContrôleurMailWindow(Contrôleur contrôleurMailWindow);
}
