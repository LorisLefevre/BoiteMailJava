import Modèle.CoucheAccèsDonnées.*;
import Contrôleur.*;
import Vue.InterfacesGraphiques.*;
import Vue.*;
public class LancementApplication
{
    public static void main(String[] args)
    {
        System.out.println("Hello World!");
        Contrôleur contrôleur = new Contrôleur(new CoucheAccèsDonnéesDAO(),  new LoginWindow());
        contrôleur.run();
    }
}
