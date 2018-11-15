package fintechqa.web1.elements;

public enum EnumElements {

    CHECKBOX(CheckBox.class.getCanonicalName()),
    SELECT(Select.class.getCanonicalName()),
    INPUTBLOCK(InputBlock.class.getCanonicalName());

    private final String nameClass;

    EnumElements(String nameClass) {
        this.nameClass = nameClass;
    }

    public String getClassName() {
        return this.nameClass;
    }
}
