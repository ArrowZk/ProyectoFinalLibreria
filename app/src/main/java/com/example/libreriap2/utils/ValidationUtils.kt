package com.example.libreriap2.utils

import android.widget.EditText

object ValidationUtils {

    fun validateBookFields(
        titleField: EditText,
        authorField: EditText,
        yearField: EditText,
        sinopsisField: EditText,
        editorialField: EditText,
        stockField: EditText,
        pagesField: EditText,
        languageField: EditText,
        recommendedAgeField: EditText,
        priceField: EditText
    ): Boolean {
        var isValid = true

        if (titleField.text.toString().isBlank()) {
            titleField.error = "El título no puede estar vacío"
            isValid = false
        }

        if (authorField.text.toString().isBlank()) {
            authorField.error = "El autor no puede estar vacío"
            isValid = false
        }

        if (yearField.text.toString().isBlank() || !yearField.text.toString().matches("\\d{4}".toRegex())) {
            yearField.error = "El año debe ser un número de 4 dígitos"
            isValid = false
        }

        if (sinopsisField.text.toString().isBlank()) {
            sinopsisField.error = "La sinopsis no puede estar vacía"
            isValid = false
        }

        if (editorialField.text.toString().isBlank()) {
            editorialField.error = "La editorial no puede estar vacía"
            isValid = false
        }

        if (stockField.text.toString().isBlank() || !stockField.text.toString().matches("\\d+".toRegex())) {
            stockField.error = "El stock debe ser un número entero"
            isValid = false
        }

        if (pagesField.text.toString().isBlank() || !pagesField.text.toString().matches("\\d+".toRegex())) {
            pagesField.error = "El número de páginas debe ser un número entero"
            isValid = false
        }

        if (languageField.text.toString().isBlank()) {
            languageField.error = "El idioma no puede estar vacío"
            isValid = false
        }

        if (recommendedAgeField.text.toString().isBlank() || !recommendedAgeField.text.toString().matches("\\d+".toRegex())) {
            recommendedAgeField.error = "La edad recomendada debe ser un número entero"
            isValid = false
        }

        if (priceField.text.toString().isBlank() || !priceField.text.toString().matches("\\d+(\\.\\d{1,2})?".toRegex())) {
            priceField.error = "El precio debe ser un número válido"
            isValid = false
        }

        return isValid
    }
}
