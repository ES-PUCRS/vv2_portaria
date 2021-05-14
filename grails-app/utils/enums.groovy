enum SearchFields {
    ENTREGA     ("ENTREGA"),
    DESCRICAO   ("DESCRICAO"),
    DATA        ("DATA"),
    APTO        ("APTO"),
    OPERADOR    ("OPERADOR"),
    RETIRADA    ("RETIRADA"),
    MORADOR     ("MORADOR");

    private final String name;
    private SearchFields(String s) {
        name = s;
    }
    String toString() {
        return this.name;
    }
}