package com.mrvilkaman.neuralnetwork.presentationlayer.utils;

import android.content.Context;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

public class FileUtils {

	public FileUtils() {
	}

	public static Observable<List<String>> getTitles(Context context) {
		return Observable.defer(() -> Observable.just(StorageUtils.getStoragePath(context)))
				.map(File::new)
				.map(File::listFiles)
				.flatMap(Observable::from)
				.map(File::getName)
				.toList()
				.subscribeOn(Schedulers.io());
	}


	public static void storeOO(Context context, String fileName, int[][] ints) {
		ObjectOutputStream out = null;
		try {
			File file = new File(StorageUtils.getStoragePath(context),fileName);
			out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(ints);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			safeClose(out);
		}
	}

	public static int[][] getOO(Context context,String fileName) {
		ObjectInputStream out = null;
		try {
			File file = new File(StorageUtils.getStoragePath(context),fileName);
			out = new ObjectInputStream(new FileInputStream(file));
			return (int[][]) out.readObject();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			safeClose(out);
		}
		return null;
	}

	private static void safeClose(Closeable out) {
		try {
			if (out != null) {
				out.close();
			}
		} catch (IOException e) {
			// do nothing
		}
	}
}
