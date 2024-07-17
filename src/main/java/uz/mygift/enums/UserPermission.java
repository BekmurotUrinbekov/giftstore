package uz.mygift.enums;

public enum UserPermission {
    SHOW("app:show"),
    MONITORING("app:monitoring"),
    PRODUCT_ADD("product:add"),
    CONTROL("app:control");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
