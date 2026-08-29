package black.java.lang;

import java.util.List;

import com.nyxbox.reflection.annotation.BClassName;
import com.nyxbox.reflection.annotation.BField;

@BClassName("java.lang.ThreadGroup")
public interface ThreadGroup {
    @BField
    List<java.lang.ThreadGroup> groups();

    @BField
    java.lang.ThreadGroup parent();
}
