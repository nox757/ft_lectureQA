package fintechqa.web1.elements;

public enum EnumElements {

    CHECKBOX(CheckBox.class.getCanonicalName(), ".//*[@data-qa-file='UICheckbox' and not(ancestor::*[@data-qa-file='UICheckbox'])]"),
    SELECT(Select.class.getCanonicalName(), ".//*[@data-qa-file='UIDropdownSelect']"),
    INPUTBLOCK(InputBlock.class.getCanonicalName(), ".//label[@data-qa-file='UIInput']"),
    BUTTON(Button.class.getCanonicalName(), ".//button[@data-qa-file='Button']");

    private final String nameClass;
    private final String baseXpath;

    EnumElements(String nameClass, String baseXpath) {
        this.nameClass = nameClass;
        this.baseXpath = baseXpath;
    }

    public String getClassName() {
        return this.nameClass;
    }

    public String getBaseXpath() {
        return this.baseXpath;
    }
}
