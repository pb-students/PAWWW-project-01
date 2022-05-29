package net.wvffle.java.pawww.pawwwproject.components.session;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public @Data class SessionSort implements Serializable {
    private String ordering = "created";
    private boolean descending = false;

    public Sort getSort () {
        Sort sort = Sort.by(ordering);

        return descending
                ? sort.descending()
                : sort.ascending();
    }
}
