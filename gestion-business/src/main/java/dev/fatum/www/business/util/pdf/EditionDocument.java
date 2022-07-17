package dev.fatum.www.business.util.pdf;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import dev.fatum.www.business.impl.manager.GroupesManagerImpl;
import dev.fatum.www.model.entities.Coordonnees;
import dev.fatum.www.model.entities.Entreprise;
import dev.fatum.www.model.entities.Personne;
import dev.fatum.www.model.entities.documents.Devis;
import dev.fatum.www.model.entities.documents.LigneDevis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class EditionDocument {

    Logger logger = LoggerFactory.getLogger(GroupesManagerImpl.class);

    private String logo = null;
    private static PdfFont helvetica = null;
    private static PdfFont helveticaBold = null;
    private static PdfFont helveticaBoldItalic = null;
    private final Integer FONT_SIZE_TITRE = 25;
    private final Integer FONT_SIZE_DOC = 12;
    private float tableRow = 300;
//    DecimalFormat df = new DecimalFormat("#.00");
    NumberFormat formatFrance = NumberFormat.getCurrencyInstance(Locale.FRANCE);

    Color blueColor = new DeviceCmyk(1f, 1f, 0, 0);
    Color whiteColor = new DeviceCmyk(0f, 0f, 0f, 0f);

    public EditionDocument(Devis devis) {
        try {
            helvetica = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            helveticaBold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
            helveticaBoldItalic = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLDOBLIQUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        createPdf(devis);

        logger.info("##### EditionDocument : Document devis créer.");
    }

    public void createPdf(Devis devis) {
        Entreprise clientPro = devis.getEntreprise();
        Personne clientParticulier = devis.getPersonne();
        Coordonnees coordonneesClient = null;
        Entreprise entreprise = null;
        if(clientPro != null) {
            coordonneesClient = clientPro.getCoordonneesList().get(0);
            entreprise = clientPro.getEntreprise();
        } else if(clientParticulier != null) {
            coordonneesClient = clientParticulier.getCoordonnees();
            entreprise = clientParticulier.getEntreprise();
        }

        String dest = "documents/entreprise-" + entreprise.getId();
        String destDevis = dest + "/devis/" + devis.getNumeroDevis() + ".pdf";
        File file = new File(destDevis);
        file.getParentFile().mkdirs();

        PdfWriter writer = null;
        try {
            writer = new PdfWriter(destDevis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PageSize ps = PageSize.A4;
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, ps);
        System.out.println("width : " + ps.getWidth() + " ; height : " + ps.getHeight());

        String destLogo = dest + "/img/logo.png";
        File logoFile  = new File(destLogo);
        if(logoFile.exists()) {
            Image logo = null;
            Image dog = null;
            try {
                float height = 0;
                float width = 0;
                logo = new Image(ImageDataFactory.create(destLogo));
                width = 120;
                height = 120 * logo.getImageHeight() / logo.getImageWidth();
                logo.setHeight(height).setWidth(width);
                logo.setFixedPosition(36, ps.getHeight() - 36 - height);

                dog = new Image(ImageDataFactory.create(destLogo));
                width = 120;
                height = 120 * dog.getImageHeight() / dog.getImageWidth();
                dog.setHeight(height).setWidth(width);
                dog.setFixedPosition(ps.getWidth() - 36 - width, ps.getHeight() - 36 - height);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            document.add(logo);
            document.add(dog);
        }

        Paragraph titre = new Paragraph("DEVIS")
                .setTextAlignment(TextAlignment.CENTER)
                .setFont(helveticaBold)
                .setMargin(40)
                .setFontSize(FONT_SIZE_TITRE);
        document.add(titre);

        float[] columnWidths = {1.6F, 1};
        Table tableInfosEntreprises = new Table(UnitValue.createPercentArray(columnWidths));
        tableInfosEntreprises.setWidth(525);
        tableInfosEntreprises.setMarginBottom(20);

//        System.out.println("entreprise : " + entreprise);
//        System.out.println("coordonnees : " + entreprise.getCoordonneesList());
        if(entreprise != null && entreprise.getCoordonneesList() != null) {
            Coordonnees coordonneesEntreprise = entreprise.getCoordonneesList().get(0);
            tableInfosEntreprises.addCell(getCellText(entreprise.getRaisonSociale() + " " + entreprise.getFormeJuridique()));
            tableInfosEntreprises.addCell(getCellTextBoldRight("Le " + devis.getDateEmission()));
            tableInfosEntreprises.addCell(getCellText(coordonneesEntreprise.getRue()));
            tableInfosEntreprises.addCell(getCellEmpty());
            tableInfosEntreprises.addCell(getCellText(coordonneesEntreprise.getCodePostal() + " " + coordonneesEntreprise.getVille()));
            tableInfosEntreprises.addCell(getCellTextBoldRight("Devis n° " + devis.getNumeroDevis()));
            tableInfosEntreprises.addCell(getCellText("SIRET : " + entreprise.getNumeroSiret()));
            tableInfosEntreprises.addCell(getCellEmpty());
            tableInfosEntreprises.addCell(getCellText("TVA n° : " +
                    (entreprise.getNumeroTva() != null ? entreprise.getNumeroTva() : "")));
        }

        if(coordonneesClient != null) {
            if(clientPro != null)
                tableInfosEntreprises.addCell(getCellText(clientPro.getRaisonSociale() + " " + clientPro.getFormeJuridique()));
            else
                tableInfosEntreprises.addCell(getCellText(clientParticulier.getCivilite() + " " + clientParticulier.getNom() + " " + clientParticulier.getPrenom()));
            tableInfosEntreprises.addCell(getCellText("Tel : " +
                    (coordonneesClient.getTel() != null ? coordonneesClient.getTel() : "")));
            tableInfosEntreprises.addCell(getCellText(coordonneesClient.getRue()));
            tableInfosEntreprises.addCell(getCellText("Email : " +
                    (coordonneesClient.getEmail() != null ? coordonneesClient.getEmail() : "")));
            tableInfosEntreprises.addCell(getCellText(coordonneesClient.getCodePostal() + " " + coordonneesClient.getVille()));
        }

        document.add(tableInfosEntreprises);

        columnWidths = new float[]{4, 1, 1, 1};
        Table tableInfosDevis = new Table(UnitValue.createPercentArray(columnWidths));
        tableInfosDevis.setMarginBottom(0);
        tableInfosDevis.setWidth(525);
        tableInfosDevis.addHeaderCell(getHeaderCell("Désignation", TextAlignment.LEFT));
        tableInfosDevis.addHeaderCell(getHeaderCell("Quantité", TextAlignment.CENTER));
        tableInfosDevis.addHeaderCell(getHeaderCell("Prix UHT", TextAlignment.CENTER));
        tableInfosDevis.addHeaderCell(getHeaderCell("Montant HT", TextAlignment.CENTER));

        List<LigneDevis> ligneDevisList = devis.getLigneDevisList();
        double totalHT = 0;
        for(LigneDevis ligneDevis : ligneDevisList) {
            tableInfosDevis.addCell(getCellTableDesignation(ligneDevis.getDesignation()));
            tableInfosDevis.addCell(getCellTableRight(String.valueOf(ligneDevis.getQuantite())));
            tableInfosDevis.addCell(getCellTableRight(formatFrance.format(ligneDevis.getPrixUnitaire())));
            tableInfosDevis.addCell(getCellTableRight(formatFrance.format(ligneDevis.getQuantite() * ligneDevis.getPrixUnitaire())));
            totalHT = totalHT + ligneDevis.getQuantite() * ligneDevis.getPrixUnitaire();
        }

        if(tableRow > 0) {
            tableInfosDevis.addCell(getCellTable("").setHeight(tableRow));
            tableInfosDevis.addCell(getCellTable(""));
            tableInfosDevis.addCell(getCellTable(""));
            tableInfosDevis.addCell(getCellTable(""));
        }

        tableInfosDevis.addCell(getCellEmpty());
        tableInfosDevis.addCell(getCellEmpty());
        tableInfosDevis.addCell(getCellTableCenter("Total HT"));
        tableInfosDevis.addCell(getCellTableRight(formatFrance.format(totalHT)));

        tableInfosDevis.addCell(getCellEmpty());
        tableInfosDevis.addCell(getCellEmpty());
        tableInfosDevis.addCell(getCellTableCenter("TVA 20,0%"));
        tableInfosDevis.addCell(getCellTableRight(formatFrance.format(totalHT * devis.getTva() / 100)));

        tableInfosDevis.addCell(getCellEmpty());
        tableInfosDevis.addCell(getCellEmpty());
        tableInfosDevis.addCell(getCellTableCenter("Acompte"));
        tableInfosDevis.addCell(getCellTableRight(formatFrance.format(devis.getAcompte())));

        tableInfosDevis.addCell(getCellEmpty());
        tableInfosDevis.addCell(getCellEmpty());
        tableInfosDevis.addCell(getCellTableCenter("Total TTC"));
        tableInfosDevis.addCell(getCellTableRight(formatFrance.format(totalHT * devis.getTva() / 100 + totalHT - devis.getAcompte())));

        document.add(tableInfosDevis);

        Paragraph p = new Paragraph("Par " + devis.getModeDeReglement() + " à Codec CAPE");
        document.add(p);
        p = new Paragraph("Devis valable trois mois à partir de la date d'émission.")
                .setFont(helveticaBold).setFontSize(FONT_SIZE_DOC);
        document.add(p);
        p = new Paragraph("Mention manuscrite : \"bon pour accord\" date et signature du client.")
                .setFont(helveticaBold).setFontSize(FONT_SIZE_DOC);
        document.add(p);

        document.close();
    }

    public Cell getCellEmpty() {
        return getCellText("", TextAlignment.LEFT);
    }

    // Texte de la page
    public Cell getCellText(String text) {
        return getCellText(text, TextAlignment.LEFT);
    }

    public Cell getCellTextBoldRight(String text) {
        return getCellText(text, TextAlignment.RIGHT).setFont(helveticaBold);
    }

    public Cell getCellText(String text, TextAlignment alignment) {
        Cell cell = new Cell().add(new Paragraph(text));
        cell.setPadding(0);
        cell.setTextAlignment(alignment);
        cell.setFont(helvetica);
        cell.setFontSize(FONT_SIZE_DOC);
        cell.setBorder(Border.NO_BORDER);
        return cell;
    }

    // Texte du tableau
    public Cell getCellTable(String text) {
        return getCellTable(text, TextAlignment.LEFT);
    }

    public Cell getCellTableCenter(String text) {
        return getCellTable(text, TextAlignment.CENTER);
    }

    public Cell getCellTableRight(String text) {
        return getCellTable(text, TextAlignment.RIGHT);
    }

    public Cell getCellTable(String text, TextAlignment alignment) {
        Cell cell = new Cell().add(new Paragraph(text));
        cell.setBorder(new SolidBorder(blueColor, 0));
        cell.setTextAlignment(alignment);
        cell.setFont(helvetica);
        cell.setFontSize(FONT_SIZE_DOC);
        return cell;
    }

    public Cell getHeaderCell(String text, TextAlignment alignment) {
        Cell cell = getCellTable(text, alignment);
        cell.setBackgroundColor(blueColor);
        cell.setFontColor(whiteColor, 1);
        cell.setFont(helveticaBoldItalic);
        return cell;
    }

    public Cell getCellTableDesignation(String text) {
        tableRow = tableRow - 20f * getTextWidth(text);
        return getCellTable(text, TextAlignment.LEFT);
    }

    public int getTextWidth(String text) {
        int textRows = 1;
        try {
            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA, PdfEncodings.WINANSI, true);
            if(font.getWidth(text, FONT_SIZE_DOC) > 296) {
                float width = 0;
                String[] parts = text.split(" ");
                for (String part : parts) {
                    width = width + font.getWidth(part + " ", FONT_SIZE_DOC);
                    if (width > 296) {
                        textRows++;
                        width = font.getWidth(part, FONT_SIZE_DOC);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textRows;
    }

}