package com.example.friv_projekat.config;

import com.example.friv_projekat.model.Igra;
import com.example.friv_projekat.model.Kategorija;
import com.example.friv_projekat.repository.IgraRepository;
import com.example.friv_projekat.repository.KategorijaRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class GameDataLoader implements CommandLineRunner {

    private final IgraRepository igraRepository;
    private final KategorijaRepository kategorijaRepository;
    private final ResourceLoader resourceLoader;

    public GameDataLoader(
            IgraRepository igraRepository,
            KategorijaRepository kategorijaRepository,
            ResourceLoader resourceLoader
    ) {
        this.igraRepository = igraRepository;
        this.kategorijaRepository = kategorijaRepository;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void run(String @NonNull ... args) throws Exception {
        // obezbedjujemo podrazumevanu kategoriju
        Kategorija podrazumevanaKategorija = kategorijaRepository.findByIme("Arkadne")
                .orElseGet(() -> {
                    Kategorija k = new Kategorija();
                    k.setIme("Arkadne");
                    k.setOpis("Klasicne arkadne igre");
                    return kategorijaRepository.save(k);
                });

        // bezbedno pretvaramo loader u resolver resursa
        Resource[] resources = ResourcePatternUtils
                .getResourcePatternResolver(resourceLoader)
                .getResources("classpath:games/*/index.html"); // putanja igre

        for (Resource resource : resources) {
            // izvlacenje naziva foldera igre iz opisa resursa
            String opisResursa = resource.getDescription(); // npr. ".../games/clicker-heroes/index.html"

            // cistimo string da bismo izolovali deo oko foldera
            String[] deloviPutane = opisResursa.split("/");
            if (deloviPutane.length < 2) continue;

            // naziv foldera se nalazi tačno ispred "index.html"
            String folderName = deloviPutane[deloviPutane.length - 2];

            // pretvaramo npr "clicker-heroes" u "Clicker Heroes"
            String lepoImeIgre = konvertujULepNaziv(folderName);

            // upis u bazu ako igra vec ne postoji
            if (!igraRepository.existsByIme(lepoImeIgre)) {
                Igra novaIgra = new Igra();
                novaIgra.setIme(lepoImeIgre);
                novaIgra.setOpis("Automatski ucitana igra iz sistema.");

                // formiramo URL i thumbnail
                novaIgra.setURL("games/" + folderName + "/index.html");
                novaIgra.setThumbnailPutanja("games/" + folderName + "/icon.png");

                novaIgra.setKategorija(podrazumevanaKategorija);
                novaIgra.setDatumDodavanja(LocalDateTime.now());
                novaIgra.setAktivna(true);

                igraRepository.save(novaIgra);
                System.out.println("[Auto-Load] Uspesno ucitana igra: " + lepoImeIgre);
            }
        }
    }

    private String konvertujULepNaziv(String folderName) {
        String[] reci = folderName.split("-");
        StringBuilder sb = new StringBuilder();
        for (String rec : reci) {
            if (!rec.isEmpty()) {
                sb.append(Character.toUpperCase(rec.charAt(0)))
                        .append(rec.substring(1))
                        .append(" ");
            }
        }
        return sb.toString().trim();
    }
}