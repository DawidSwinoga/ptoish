package pl.dmcs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person implements Serializable, Externalizable {
    private String name;
    private String lastName;
    private Instant birthday;
    private long age;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);
        out.writeUTF(lastName);
        out.writeLong(birthday.getEpochSecond());
        out.writeLong(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        name = in.readUTF();
        lastName = in.readUTF();
        birthday = Instant.ofEpochSecond(in.readLong());
        age = in.readLong();
    }
}
