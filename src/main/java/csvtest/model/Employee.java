package csvtest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//ssoid	ts	grp	type	subtype	url	orgid	formid	code	ltpa	sudirresponse	ymdh

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empl_seq_gen")
    @SequenceGenerator(name = "empl_seq_gen", sequenceName = "seq_employee_id", allocationSize = 1)
    private int id;

    @Column(name = "SSOID")
    private String ssoid;

    @Column(name = "TS")
    private String ts;

    @Column(name = "GRP")
    private String grp;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "SUBTYPE")
    private String subtype;

    @Column(name = "URL")
    private String url;

    @Column(name = "ORGID")
    private String orgid;

    @Column(name = "FOMRID")
    private String formid;

    @Column(name = "CODE")
    private String code;

    @Column(name = "LTPA")
    private String ltpa;

    @Column(name = "UDIRRESPONSEA")
    private String sudirresponsea;

    @Column(name = "YMDH")
    private String ymdh;
}
