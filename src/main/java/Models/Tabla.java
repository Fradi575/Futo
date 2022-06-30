package Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.tinylog.Logger;

/**
 * Tábla osztály, amely a mezőket tárolja, és azokhoz tartozó metódosukat.
 */
@Getter
@Setter
@AllArgsConstructor
public class Tabla {

    public static final int URES = 0;
    public static final int FEHER = 1;
    public static final int FEKETE = 2;

    private int[][] mezok = new int[5][4];

    /**
     * Konstruktor, amely a táblát egy célállapothoz közeli állapotba helyezi.
     */
    public Tabla() {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 4; j++) {
                mezok[i][j] = URES;
            }

        for (int k = 0; k < 2; k++) {
            mezok[0][k] = FEKETE;
            mezok[4][k] = FEHER;
        }

        for (int k = 1; k < 3; k++) {
            mezok[1][k] = FEKETE;
            mezok[3][k] = FEHER;
        }
    }

    /**
     * Bábú mozgatása a táblán.
     *
     * @param honnan_x A bábú kezdő poziciójának sora
     * @param honnan_y A bábú kezdő poziciójának oszlopa
     * @param hova_x   A célmező poziciójának sora
     * @param hova_y   A célmező poziciójának oszlopa
     */

    public void mozgat(int honnan_x, int honnan_y, int hova_x, int hova_y) {


        int jelenlegi_babu = mezok[honnan_x][honnan_y];

            mezok[honnan_x][honnan_y] = URES;
            mezok[hova_x][hova_y] = jelenlegi_babu;
            Logger.info("Sikeres lépés!");


    }

    /**
     * @param honnan_x A bábú kezdő poziciójának sora
     * @param honnan_y A bábú kezdő poziciójának oszlopa
     * @param hova_x   A célmező poziciójának sora
     * @param hova_y   A célmező poziciójának oszlopa
     * @return megvalósítható-e a lépés, vagy nem
     */

    public boolean szabade(int honnan_x, int honnan_y, int hova_x, int hova_y) {

        if (mezok[hova_x][hova_y] != URES) {
            return false;

        }
        if (!((hova_x - honnan_x == hova_y - honnan_y) || (-hova_x + honnan_x == hova_y - honnan_y))) {
            return false;

        }

        if (mezok[honnan_x][honnan_y] == URES) {
            return false;

        }

        int jelenleg_babu = mezok[honnan_x][honnan_y];
        int ellenseges;
        if (jelenleg_babu == FEHER) {
            ellenseges = FEKETE;
        } else {
            ellenseges = FEHER;
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (mezok[i][j] == ellenseges) {
                    if (Math.abs(hova_x - i) == Math.abs(hova_y - j)) {
                        return false;
                    }
                }
            }
        }
        return true;

    }

    /**
     *
     * @param honnan_x
     * @param honnan_y
     * @param hova_x
     * @param hova_y
     * Ez a fv ellenőrzi le, hogy léphet-e úgy a futó, hogy másik bábut átugorhasson.
     */
    public boolean atugrasellenoriz(int honnan_x, int honnan_y, int hova_x, int hova_y) {
        // itt megnezi hog ne ugrojon at semmilyen  babut
        // bal felso atlo
        String holVanAhovaMegyek = "sehol";
        if (honnan_x > hova_x && hova_y < honnan_y) holVanAhovaMegyek = "balFelul";
        else if (honnan_x > hova_x && hova_y > honnan_y) holVanAhovaMegyek = "jobbFelul";
        else if (honnan_x < hova_x && hova_y < honnan_y) holVanAhovaMegyek = "balAlul";
        else if (honnan_x < hova_x && hova_y > honnan_y) holVanAhovaMegyek = "jobbAlul";

        switch (holVanAhovaMegyek) {
            case "balFelul":
                while (honnan_x != hova_x && honnan_y != hova_y) {
                    honnan_x -= 1;
                    honnan_y -= 1;
                    if (mezok[honnan_x][honnan_y] != 0) return false;
                }
                break;
            case "jobbFelul":
                while (honnan_x != hova_x && honnan_y != hova_y) {
                    honnan_x -= 1;
                    honnan_y += 1;
                    if (mezok[honnan_x][honnan_y] != 0) return false;
                }
                break;
            case "balAlul":
                while (honnan_x != hova_x && honnan_y != hova_y) {
                    honnan_x += 1;
                    honnan_y -= 1;
                    if (mezok[honnan_x][honnan_y] != 0) return false;
                }
                break;
            case "jobbAlul":
                while (honnan_x != hova_x && honnan_y != hova_y) {
                    honnan_x += 1;
                    honnan_y += 1;
                    if (mezok[honnan_x][honnan_y] != 0) return false;
                }
                break;

        }
        return true;
    }

    /**
     * Meg van-e oldva a tábla.
     * @return célállapotban vannak-e a bábúk a táblák. Célállapot, ha négy fehér az első sorban, a feketék pedig az utolsókban vannak
     */
    public boolean megoldva() {
        for (int i = 0; i < 4; i++) {
            if (mezok[0][i] != FEKETE)
                return false;
            if (mezok[4][i] != FEHER)
                return false;
        }
        return true;
    }

    /**
     * Függvény, amely a mezok[i][j]-nek értéket ad.
     *    @param i A mezok i. eleme
     *    @param j A mezok j. eleme
     *    @param ertek, hogy a mezok[i][j]-nek milyen értéket adjunk
     *    */
    public void setMezo(int i, int j, int ertek) {
        mezok[i][j] = ertek;
    }

    /**
     * toString metódus
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                str += mezok[i][j];
            }
            str += '\n';
        }
        return str;
    }
}
