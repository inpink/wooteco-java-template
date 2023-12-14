package christmas.constants;

public enum PatternConstants {
    THOUSAND_SEPARATOR("###,##0"),
    THOUSAND_SEPARATOR_WITH_ROUNDED_UNTIL_FIRST_DECIMAL(THOUSAND_SEPARATOR.pattern + ".0");

    private final String pattern;

    PatternConstants(final String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
