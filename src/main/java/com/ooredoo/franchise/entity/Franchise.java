package com.ooredoo.franchise.entity;

import com.ooredoo.franchise.entity.utils.OoredooBaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "franchise")
public class Franchise extends OoredooBaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Applicants name is mandatory.")
    @Column(name = "applicants_name")
    private String applicantsName;

    @NotBlank(message = "Id number is mandatory.")
    @Column(name = "id_number")
    private String idNumber;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "contact_address")
    private String contactAddress;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Email(message = "Email format is not correct.")
    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "interest_about_brand")
    private String interestAboutBrand;

    @Column(name = "investment_amount")
    private Double investmentAmount;

    @Column(name = "preferred_operate_location")
    private String preferredOperateLocation;

    @Column(name = "notes", columnDefinition = "longtext")
    private String notes;


    public Franchise() {
    }

    public Franchise(@NotBlank(message = "Applicants name is mandatory.") String applicantsName, @NotBlank(message = "Id number is mandatory.") String idNumber, @Email(message = "Email format is not correct.") String contactEmail) {
        this.applicantsName = applicantsName;
        this.idNumber = idNumber;
        this.contactEmail = contactEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicantsName() {
        return applicantsName;
    }

    public void setApplicantsName(String applicantsName) {
        this.applicantsName = applicantsName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getInterestAboutBrand() {
        return interestAboutBrand;
    }

    public void setInterestAboutBrand(String interestAboutBrand) {
        this.interestAboutBrand = interestAboutBrand;
    }

    public Double getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(Double investmentAmount) {
        this.investmentAmount = investmentAmount;
    }

    public String getPreferredOperateLocation() {
        return preferredOperateLocation;
    }

    public void setPreferredOperateLocation(String preferredOperateLocation) {
        this.preferredOperateLocation = preferredOperateLocation;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Franchise{" +
                "id=" + id +
                ", applicantsName='" + applicantsName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", birthDate=" + birthDate +
                ", contactAddress='" + contactAddress + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", interestAboutBrand='" + interestAboutBrand + '\'' +
                ", investmentAmount=" + investmentAmount +
                ", preferredOperateLocation='" + preferredOperateLocation + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

}
