package com.mmik.challenge.capita.domain;

import com.mmik.challenge.capita.domain.enumtype.Checked;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by mmik on 29/01/2017.
 */
@Entity
@Table(name = "borrower")
public class Borrower implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "memberid")
    private String memberId;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "barcode")
    private String barCode;

    @Column(name = "type")
    private String type;

    @Column(name = "checked")
    @Enumerated(EnumType.STRING)
    private Checked checked;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Checked getChecked() {
        return this.checked;
    }

    public void setChecked(Checked checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return String.format(
                "Borrower{" +
                        "id=%d, " +
                        "memberId='%s', "+
                        "fullName='%s', " +
                        "barCode='%s'" +
                        "type='%s'" +
                        "checked='%s'" +
                        '}',
                id,
                memberId,
                fullName,
                barCode,
                type,
                checked
        );
    }
}
