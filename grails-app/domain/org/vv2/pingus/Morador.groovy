package org.vv2.pingus

class Morador {

    String  nome
    String  rg
    Integer apto
    boolean inativo

    static constraints = {
        // Nome: Must be non-null, only letters (with accents), and single spaces between names
        nome nullable: false, matches: /^[A-Za-zÀ-ÿ]+(?: [A-Za-zÀ-ÿ]+)*$/

        // RG: Allows both formatted (12.345.678-9 or 12.345.678-X) and unformatted (123456789 or 12345678X)
        rg nullable: false, matches: /^\d{2}\.\d{3}\.\d{3}-[\dXx]$|^\d{8}[\dXx]$/

        apto nullable: false, min: 0

        inativo() // no constraints; just leaving it here for completeness
    }

    static mapping = {
        version false
    }

    @Override
    String toString() {
        nome
    }
}
