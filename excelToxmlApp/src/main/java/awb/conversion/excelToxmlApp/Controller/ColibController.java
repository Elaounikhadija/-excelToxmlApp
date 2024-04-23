package awb.conversion.excelToxmlApp.Controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import awb.conversion.excelToxmlApp.Model.Colib;
import awb.conversion.excelToxmlApp.Service.ColibService;
import awb.conversion.excelToxmlApp.util.ExcelUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/api")
public class ColibController {
    @Autowired
    ColibService colibService;

    @PostMapping("/excel/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ExcelUtility.hasExcelFormat(file)) {
            try {
                colibService.save(file);
                message = "The Excel file is uploaded: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception exp) {
                message = "The Excel file is not upload: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @GetMapping("/student-list")
    public ResponseEntity<?> getStudents() {
        Map<String, Object> respStu = new LinkedHashMap<String, Object>();
        List<Colib> studList = colibService.findAll();
        if (!studList.isEmpty()) {
            respStu.put("status", 1);
            respStu.put("data", studList);
            return new ResponseEntity<>(respStu, HttpStatus.OK);
        } else {
            respStu.clear();
            respStu.put("status", 0);
            respStu.put("message", "Data is not found");
            return new ResponseEntity<>(respStu, HttpStatus.NOT_FOUND);
        }

    }
}
