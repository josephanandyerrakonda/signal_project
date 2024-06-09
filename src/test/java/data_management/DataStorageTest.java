package data_management;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.data_management.DataStorage;
import com.data_management.PatientRecord;

import java.util.List;

class DataStorageTest {

    @Test
    void testAddAndGetRecords() {
        DataReader reader = new MockDataReader();
        // DataReader reader
        DataStorage storage = new DataStorage(reader);

        try {
            reader.readData(storage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        storage.addPatientData(1, 100.0, "WhiteBloodCells", 1714376789050L);
        storage.addPatientData(1, 200.0, "WhiteBloodCells", 1714376789051L);

        List<PatientRecord> records = storage.getRecords(1, 1714376789050L, 1714376789051L);
        assertEquals(2, records.size()); // Check if two records are retrieved
        assertEquals(100.0, records.get(0).getMeasurementValue()); // Validate first record
    }
}   class MockDataReader implements DataReader {
    @Override
    public void readData(DataStorage dataStorage) {
        // Add mock data to storage
        dataStorage.addPatientData(1, 75.0, "HeartRate", 1714376789000L);
        dataStorage.addPatientData(1, 120.0, "HeartRate", 1714376789001L);
    }
}
