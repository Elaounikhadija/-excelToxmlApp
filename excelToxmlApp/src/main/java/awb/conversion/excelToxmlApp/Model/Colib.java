package awb.conversion.excelToxmlApp.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Colib {

    @Id
    @GeneratedValue
    private int numero_declaration;

    private String montant_taxe;

    private String montant_brut;

    private Date date_operation;

}
