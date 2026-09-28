package org.ecumenopolismc.lib.tensorfield;

import org.ejml.data.FMatrix2;

public interface DirectionMap {
    FMatrix2 getPrimaryDirection(FMatrix2 point);
    FMatrix2 getSecondaryDirection(FMatrix2 point);
}
