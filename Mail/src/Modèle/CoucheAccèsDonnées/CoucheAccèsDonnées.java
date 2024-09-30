package Modèle.CoucheAccèsDonnées;

import Modèle.ClassesMétier.*;

import java.util.List;

public interface CoucheAccèsDonnées
{
    void EnvoyerMailGmail(String Expediteur, String Destinataire, String Sujet, String Message);

    void EnvoyerMailGmailPieceJointe(String Expediteur, String Destinataire, String Sujet, String Message, List<String> PieceJointe);

    void EnvoyerMail(String Hote, String Expediteur, String Destinataire, String Sujet, String Message);

    void EnvoyerMailPieceJointe(String Expediteur, String Destinataire, String Sujet, String Message, List<String> PieceJointe);
}
