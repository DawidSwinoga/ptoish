package pl.dmcs.memoryleak;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawid on 09.12.2018 at 15:27.
 */
public class PhantomReferenceStore {
    public static List<PhantomReference> references = new ArrayList<>();
}
