package com.example.dachuang;

public class test {
    public static void main(String[] args) {
        /*InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("application.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = builder.build(inputStream);
        SqlSession sqlSession = build.openSession();
        MonitorDao monitorDao = sqlSession.getMapper(MonitorDao.class);
        List<Monitor> res = monitorDao.findAll();
        System.out.println(res);*/

        //System.out.println(DachuangApplication.ac.getBean(MonitorController.class).test());
        String Time = "5-24-13";
        String Date = "1971-2-5";
        String[] strs1 = Time.split("-");
        String[] strs2 = Date.split("-");


        int ans1 = (Integer.parseInt(strs1[0]) - 8) * 3600 + Integer.parseInt(strs1[1]) * 60 + Integer.parseInt(strs1[2]) +
                (Integer.parseInt(strs2[0]) - 1970) * 31536000 + (Integer.parseInt(strs2[1]) - 1) * 2678400 + (Integer.parseInt(strs2[2]) - 1) * 86400;

        System.out.println(ans1);
    }
}
