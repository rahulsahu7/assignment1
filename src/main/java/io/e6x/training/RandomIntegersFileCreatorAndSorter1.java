package io.e6x.training;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class RandomIntegersFileCreatorAndSorter1 {

    private static final long FILE_SIZE = 2L * 1024L * 1024L * 1024L; // 2GB in bytes
    private static final int INTEGER_SIZE_BYTES = 4; // 32 bits = 4 bytes

    public static void main(String[] args) {
        String filename = "random_integers.dat";
        String sortedFilename = "sorted_integers.dat";

        // Create file with random integers
        try (DataOutputStream outputStream = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(filename)))) {

            Random random = new Random();
            long bytesWritten = 0;

            while (bytesWritten < FILE_SIZE) {
                int randomInt = random.nextInt();
                outputStream.writeInt(randomInt);
                bytesWritten += INTEGER_SIZE_BYTES;
            }

            System.out.println("File created successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read and sort the file
        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(filename));
             DataOutputStream sortedOutputStream = new DataOutputStream(
                     new BufferedOutputStream(new FileOutputStream(sortedFilename)))) {

            long totalIntegers = FILE_SIZE / INTEGER_SIZE_BYTES;
            int[] integers = new int[(int) totalIntegers];

            for (int i = 0; i < totalIntegers; i++) {
                integers[i] = inputStream.readInt();
            }

            Arrays.sort(integers);

            // Write sorted integers to the new file
            for (int i = 0; i < totalIntegers; i++) {
                sortedOutputStream.writeInt(integers[i]);
            }

            System.out.println("File sorted and created successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

