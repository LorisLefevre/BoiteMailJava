package Vue;

import Contrôleur.*;
import Vue.InterfacesGraphiques.MailWindow;

public class VueMailWindowConsole implements VueMailWindow
{

    private MailWindow mailwindow;

    public VueMailWindowConsole(MailWindow mailwindow)
    {
        this.mailwindow = mailwindow;
    }
    @Override
    public void run()
    {

    }

    @Override
    public void setContrôleurMailWindow(Contrôleur contrôleurMailWindow)
    {
        contrôleurMailWindow = contrôleurMailWindow;
    }
}
