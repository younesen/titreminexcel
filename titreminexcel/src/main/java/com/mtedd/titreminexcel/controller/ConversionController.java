package com.mtedd.titreminexcel.controller;

import com.mtedd.titreminexcel.model.Procedure;
import com.mtedd.titreminexcel.service.ExcelToProcedureConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/conversion")
@CrossOrigin(origins = "*")
public class ConversionController {

    private final ExcelToProcedureConverter excelToProcedureConverter;

    public ConversionController(ExcelToProcedureConverter excelToProcedureConverter) {
        this.excelToProcedureConverter = excelToProcedureConverter;
    }

    // API 1: Conversion avec feuille par défaut (première feuille)
    @PostMapping("/excel-to-procedure")
    public ResponseEntity<Map<String, Object>> convertExcelToProcedure(
            @RequestParam("file") MultipartFile file) {
        return convertExcel(file, 0);
    }

    // API 2: Conversion avec index de feuille spécifique
    @PostMapping("/excel-to-procedure/sheet-index")
    public ResponseEntity<Map<String, Object>> convertExcelToProcedureWithSheetIndex(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "sheetIndex", defaultValue = "0") int sheetIndex) {
        return convertExcel(file, sheetIndex);
    }

    // API 3: Conversion avec nom de feuille spécifique
    @PostMapping("/excel-to-procedure/sheet-name")
    public ResponseEntity<Map<String, Object>> convertExcelToProcedureWithSheetName(
            @RequestParam("file") MultipartFile file,
            @RequestParam("sheetName") String sheetName) {
        try {
            if (file.isEmpty()) {
                return errorResponse("Le fichier est vide");
            }

            if (!isExcelFile(file)) {
                return errorResponse("Seuls les fichiers Excel (.xlsx) sont supportés");
            }

            Procedure result = excelToProcedureConverter.convertExcelToProcedure(file, sheetName);
            return ResponseEntity.ok(successResponse(result));

        } catch (Exception e) {
            return errorResponse("Erreur lors de la conversion: " + e.getMessage());
        }
    }

    // API 4: Conversion multi-feuilles automatique
    @PostMapping("/excel-to-procedure/multi-sheet")
    public ResponseEntity<Map<String, Object>> convertMultiSheetExcel(
            @RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return errorResponse("Le fichier est vide");
            }

            if (!isExcelFile(file)) {
                return errorResponse("Seuls les fichiers Excel (.xlsx) sont supportés");
            }

            Procedure result = excelToProcedureConverter.convertMultiSheetExcel(file);
            return ResponseEntity.ok(successResponse(result));

        } catch (Exception e) {
            return errorResponse("Erreur lors de la conversion multi-feuilles: " + e.getMessage());
        }
    }

    // API 5: Lister toutes les feuilles disponibles
    @PostMapping("/sheet-names")
    public ResponseEntity<Map<String, Object>> getSheetNames(
            @RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return errorResponse("Le fichier est vide");
            }

            if (!isExcelFile(file)) {
                return errorResponse("Seuls les fichiers Excel (.xlsx) sont supportés");
            }

            List<String> sheetNames = excelToProcedureConverter.getSheetNames(file);
            Map<String, Object> response = successResponse(sheetNames);
            response.put("totalSheets", sheetNames.size());
            response.put("message", "Liste des feuilles récupérée avec succès");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return errorResponse("Erreur lors de la récupération des feuilles: " + e.getMessage());
        }
    }

    // API 6: Explorer la structure complète du fichier Excel
    @PostMapping("/explore-structure")
    public ResponseEntity<Map<String, Object>> exploreExcelStructure(
            @RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return errorResponse("Le fichier est vide");
            }

            if (!isExcelFile(file)) {
                return errorResponse("Seuls les fichiers Excel (.xlsx) sont supportés");
            }

            Object result = excelToProcedureConverter.exploreExcelStructure(file);
            Map<String, Object> response = successResponse(result);
            response.put("message", "Structure du fichier analysée avec succès");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return errorResponse("Erreur lors de l'analyse de la structure: " + e.getMessage());
        }
    }

    // API 7: Test de santé étendu
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "OK");
        response.put("service", "Excel to Procedure Converter - Multi Sheets");
        response.put("version", "2.0.0");
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        response.put("features", List.of(
                "Conversion mono-feuille",
                "Conversion multi-feuilles",
                "Sélection par index/nom de feuille",
                "Exploration de structure",
                "Listage des feuilles"
        ));
        return ResponseEntity.ok(response);
    }

    // API 8: Informations sur le service
    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getServiceInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("serviceName", "Excel to Procedure Converter");
        info.put("description", "Service de conversion de fichiers Excel vers des objets Procedure structurés");
        info.put("version", "2.0.0");
        info.put("supportedFormats", List.of(".xlsx"));
        info.put("maxFileSize", "10MB");
        info.put("features", Map.of(
                "singleSheet", "Conversion depuis une feuille spécifique",
                "multiSheet", "Conversion automatique multi-feuilles",
                "sheetExploration", "Exploration de la structure Excel",
                "flexibleSelection", "Sélection par index ou nom de feuille"
        ));
        return ResponseEntity.ok(successResponse(info));
    }

    // Méthode privée pour la conversion par index
    private ResponseEntity<Map<String, Object>> convertExcel(MultipartFile file, int sheetIndex) {
        try {
            if (file.isEmpty()) {
                return errorResponse("Le fichier est vide");
            }

            if (!isExcelFile(file)) {
                return errorResponse("Seuls les fichiers Excel (.xlsx) sont supportés");
            }

            Procedure result = excelToProcedureConverter.convertExcelToProcedure(file, sheetIndex);
            Map<String, Object> response = successResponse(result);
            response.put("sheetIndex", sheetIndex);
            response.put("conversionType", "single-sheet");

            return ResponseEntity.ok(response);

        } catch (IndexOutOfBoundsException e) {
            return errorResponse("Index de feuille invalide. Vérifiez le nombre de feuilles disponibles.");
        } catch (Exception e) {
            return errorResponse("Erreur lors de la conversion: " + e.getMessage());
        }
    }

    // Méthodes utilitaires
    private boolean isExcelFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        return fileName != null && fileName.toLowerCase().endsWith(".xlsx");
    }

    private Map<String, Object> successResponse(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Opération réussie");
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        response.put("data", data);
        return response;
    }

    private ResponseEntity<Map<String, Object>> errorResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("error", message);
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        return ResponseEntity.badRequest().body(response);
    }

    // Méthode pour gérer les exceptions globales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("error", "Erreur interne du serveur: " + ex.getMessage());
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        response.put("type", ex.getClass().getSimpleName());

        // Log détaillé pour le débogage
        System.err.println("❌ Erreur dans ConversionController: " + ex.getMessage());
        ex.printStackTrace();

        return ResponseEntity.internalServerError().body(response);
    }
}