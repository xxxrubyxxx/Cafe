package com.ruby.common;

import com.ruby.cafeshop.Cafe;
import com.ruby.cafeshop.Cappuccino;
import com.ruby.constents.CafeConstents;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

public class CafeScan {

    public static void scanCafe(String packageName) {
        String packageFilePath = packageName.replace(".", "/");
        File packageFile = new File(packageFilePath);
        if (!packageFile.exists()) {
            throw new IllegalArgumentException("Package not found");
        }

        try {
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packageName);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    File file = new File(filePath);
                    List<File> fileList = new ArrayList<>();
                    fetchFileList(file, fileList);

                    for (File f : fileList) {
                        String fileName = f.getAbsolutePath();
                        if (fileName.endsWith(".class")) {
                            String noSuffixFileName = fileName.substring(8 + fileName.lastIndexOf("class"), fileName.indexOf(".class"));
                            String filePackage = noSuffixFileName.replaceAll("\\\\", ".");
                            try {
                                Class<?> clazz = Class.forName(filePackage);
                                Method[] methods = clazz.getMethods();
                                for (Method method : methods) {
                                    Router annotation = method.getAnnotation(Router.class);
                                    if (annotation == null) {
                                        continue;
                                    }

                                    Cappuccino cappuccino = new Cappuccino();
                                    String routerName = annotation.value();
                                    Cafe cafe = new Cafe();
                                    cafe.setName(routerName);
                                    cafe.setMethod(method);
                                    cafe.setType(CafeConstents.ROUTER);
                                    cappuccino.makeCappuccino(routerName, cafe);
                                }
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fetchFileList(File dir, List<File> fileList) {
        if (dir.isDirectory()) {
            for (File f : Objects.requireNonNull(dir.listFiles())) {
                fetchFileList(f, fileList);
            }
        } else {
            fileList.add(dir);
        }
    }
}
