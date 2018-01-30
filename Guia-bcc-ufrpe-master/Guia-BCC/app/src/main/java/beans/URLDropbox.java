package beans;

/**
 * Created by Fabio on 29/01/2018.
 * Classe para guardar o link da pasta do dropbox das disciplinas cadastradas
 * no banco de questões
 */

public class URLDropbox {
    private static final String URL_ALGORITMO = "https://www.dropbox.com/sh/kr3lz5x4sbw0u3r/AABomTTxBbh1_f3DKS1S2fo7a?dl=0";
    private static final String URL_BD = "https://www.dropbox.com/sh/gykr2gqukm5bfj7/AAAGJqecGu4QvyiFO0_N-G_Ia?dl=0";
    private static final String URL_CALCULO_NII = "https://www.dropbox.com/sh/quqoyw9rppmz559/AABlPiH6Z2npCh09Gjfho5Tya?dl=0";
    private static final String URL_CIRC_DIGI = "https://www.dropbox.com/sh/74d69xab8g74e0q/AABLBGC7xk9zMD9TD4qVvyBia?dl=0";
    private static final String URL_MD1 = "https://www.dropbox.com/sh/lkwybsl3ybf1i5g/AAAqiN8UzWCXDMqQdbm_mMGda?dl=0";
    private static final String URL_ENG_SOFT = "https://www.dropbox.com/sh/ewregswxtuosyyi/AAB5JJh9Tl4Iv1z0PXP5L2mna?dl=0";
    private static final String URL_CALCULO_NI = "https://www.dropbox.com/sh/f1u0evliyt9oyf9/AABdVrqYROdMn1kLqoaUuOl3a?dl=0";
    private static final String URL_ARQUITETURA = "https://www.dropbox.com/sh/5pj7m5j0mei1rrd/AAASTd6aJEASczcXckpjyRsFa?dl=0";
    private static final String URL_MD2 = "https://www.dropbox.com/sh/9suvsabbvtpdc8s/AAAhsoAJiWW-HwDA5gX6Wpupa?dl=0";
    private static final String URL_PAA = "https://www.dropbox.com/sh/856gr1jgyzk9xof/AADLf0z61-KeFxX6I-sW7eRoa?dl=0";
    private static final String URL_PARADIGMAS = "https://www.dropbox.com/sh/clvs61jzkspj2at/AAD0fAffBtIeATZcRTF1kdHCa?dl=0";
    private static final String URL_IP1 = "https://www.dropbox.com/sh/v5hk6qdc83nibbi/AAB54DnZqTjQ3FdXOLGRuEXEa?dl=0";
    private static final String URL_REDES = "https://www.dropbox.com/sh/nlke07tcn5iphp9/AABjjXM2UHCbZkr7RniAhdxFa?dl=0";
    private static final String URL_TEORIA = "https://www.dropbox.com/sh/o90q6a86x77dt71/AABOnb1fpg3l5ICB5I6Jot9Ca?dl=0";
    private static final String URL_FISICA = "https://www.dropbox.com/sh/4r14h1ym91ev3xd/AAB0t8PrY-4L2CMzR4QkZLEEa?dl=0";
    private static final String URL_ALGEBRA = "https://www.dropbox.com/sh/d4206yyl3dtz94l/AAAUwoSjdM3u6Uxtjr6EEmHBa?dl=0";

    public static String getUrl(String nomeDisciplina){
        String url = "";
        switch (nomeDisciplina){
            case "ALGORITMOS E ESTRUTURAS DE DADOS":
                url = URL_ALGORITMO;
                break;
            case "BANCO DE DADOS S":
                //o nome da disciplina não sei se está correto,
                //pois ela não está aparecendo no ava para eu conferir
                url = URL_BD;
                break;
            case "CÁLCULO DIFERENC. E INTEGR. II L":
                url = URL_CALCULO_NII;
                break;
            case "CIRCUITOS DIGITAIS":
                url = URL_CIRC_DIGI;
                break;
            case "MATEMÁTICA DISCRETA I":
                url = URL_MD1;
                break;
            case "ENGENHARIA DE SOFTWARE":
                url = URL_ENG_SOFT;
                break;
            case "CÁLCULO DIFERENC. E INTEGR. I L":
                url = URL_CALCULO_NI;
                break;
            case "ARQUITETURA E ORGANIZAÇÃO DE COMPUTADORES":
                url = URL_ARQUITETURA;
                break;
            case "MATEMÁTICA DISCRETA II":
                url = URL_MD2;
                break;
            case "PROJETO E ANÁLISE DE ALGORITMOS":
                url = URL_PAA;
                break;
            case "PARADIGMAS DE PROGRAMAÇÃO":
                url = URL_PARADIGMAS;
                break;
            case "INTRODUÇÃO À PROGRAMAÇÃO I":
                url = URL_IP1;
                break;
            case "REDES DE COMPUTADORES":
                url = URL_REDES;
                break;
            case "TEORIA DA COMPUTAÇÃO":
                url = URL_TEORIA;
                break;
            case "FÍSICA APLICADA À COMPUTAÇÃO":
                url = URL_FISICA;
                break;
            case "ÁLGEBRA VETORIAL E LINEAR PARA COMPUTAÇÃO":
                url = URL_ALGEBRA;
                break;
            default:
                break;
        }
        return url;
    }
}
