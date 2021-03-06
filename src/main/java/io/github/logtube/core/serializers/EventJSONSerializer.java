package io.github.logtube.core.serializers;

import io.github.logtube.core.IEvent;
import io.github.logtube.core.IEventSerializer;
import io.github.logtube.utils.ExtraJsonWriter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class EventJSONSerializer implements IEventSerializer {

    @Override
    public void serialize(@NotNull IEvent e, @NotNull Writer w) throws IOException {
        ExtraJsonWriter j = new ExtraJsonWriter(w);
        j.beginObject();
        j.name("t").value(e.getTimestamp());
        j.name("h").value(e.getHostname());
        j.name("e").value(e.getEnv());
        j.name("p").value(e.getProject());
        j.name("o").value(e.getTopic());
        j.name("c").value(e.getCrid());
        j.name("s").value(e.getCrsrc());
        if (e.getMessage() != null) {
            j.name("m").value(e.getMessage());
        }
        if (e.getKeyword() != null) {
            j.name("k").value(e.getKeyword());
        }
        if (e.getExtra() != null) {
            j.name("x").beginObject();
            for (Map.Entry<String, Object> entry : e.getExtra().entrySet()) {
                j.name(entry.getKey());
                j.value(entry.getValue());
            }
            j.endObject();
        }
        j.endObject();
    }

}
