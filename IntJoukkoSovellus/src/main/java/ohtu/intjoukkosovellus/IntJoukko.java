package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int taulukonPituus = 5, // aloitustalukon koko
            oletusPituudenKasvu = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatusKoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm = 0;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        ljono = new int[taulukonPituus];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        this.kasvatusKoko = oletusPituudenKasvu;
    }

    public IntJoukko(int taulukonPituus) {
        if (taulukonPituus < 0) {
            return;
        }
        ljono = new int[taulukonPituus];
        this.kasvatusKoko = oletusPituudenKasvu;
    }

    public IntJoukko(int taulukonPituus, int kasvatuskoko) {
        if (taulukonPituus < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kasvatuskoko väärin");//heitin vaan jotain :D
        }

        ljono = new int[taulukonPituus];
        this.kasvatusKoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            ljono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % ljono.length == 0) {
                ljono = luoPidempiTaulukko(ljono);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return true;
            }
        }

        return false;
    }

    public int[] luoPidempiTaulukko(int[] vanhaTaulukko) {
        int[] uusiTaulukko = new int[alkioidenLkm + kasvatusKoko];

        kopioiTaulukko(vanhaTaulukko, uusiTaulukko);

        return uusiTaulukko;
    }

    public boolean poista(int luku) {
        int luvunSijanti = luvunSijaintiTaulukossa(luku);

        if (luvunSijanti >= 0) {
            for (int i = luvunSijanti; i < alkioidenLkm - 1; i++) {
                ljono[i] = ljono[i + 1];
            }

            alkioidenLkm--;
            return true;
        }

        return false;
    }

    public int luvunSijaintiTaulukossa(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return i;
            }
        }

        return -1;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm; i++) {
            tuotos += ljono[i];
            if (i < alkioidenLkm - 1) {
                tuotos += ", ";
            }
        }
        tuotos += "}";
        return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(i);
        }

        return z;
    }

}
