package com.blandygbc.adopet.domain.role;

public enum BasicRoles {
    ADMINISTRATOR(1l),
    SHELTER(2l),
    TUTOR(3l);

    private Long id;

    private BasicRoles(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }
}
