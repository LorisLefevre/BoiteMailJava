package Vue;

import Modèle.ClassesMétier.*;
import Modèle.CoucheAccèsDonnées.*;
import Contrôleur.*;
import Vue.InterfacesGraphiques.MailWindow;

public interface VueMailBoxWindow
{
    void run();

    void setContrôleurMailBoxWindow(Contrôleur contrôleurMailBoxWindow);
}
