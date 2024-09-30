package Vue;

import Contrôleur.Contrôleur;
import Vue.InterfacesGraphiques.MailBoxWindow;

public class VueMailBoxWindowConsole implements VueMailBoxWindow
{
    private MailBoxWindow mailBoxWindow;

    public VueMailBoxWindowConsole(MailBoxWindow mailBoxWindow)
    {
        this.mailBoxWindow = mailBoxWindow;
    }

    @Override
    public void run()
    {

    }

    @Override
    public void setContrôleurMailBoxWindow(Contrôleur contrôleurMailBoxWindow)
    {
        contrôleurMailBoxWindow = contrôleurMailBoxWindow;
    }
}
