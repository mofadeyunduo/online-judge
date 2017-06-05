package per.piers.onlineJudge.util;

import per.piers.onlineJudge.model.TestInfo;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.SimpleKMeans;
import weka.core.EuclideanDistance;
import weka.core.Instances;
import weka.experiment.InstanceQuery;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FindPlagiarismAlgorithm {

    public String cluster(int qid, TestInfo[] testInfos) throws Exception {
        InstanceQuery query = new InstanceQuery();
        final Properties properties = new Properties();
        try (InputStream inputStream = MailUtil.class.getClassLoader().getResourceAsStream("config/mybatis/applications.properties");) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        query.setUsername(properties.getProperty("jdbc.username"));
        query.setPassword(properties.getProperty("jdbc.password"));
        query.setQuery("SELECT code FROM tests WHERE qid = " + qid + ";");
        Instances data = query.retrieveInstances();

        StringToWordVector filter = new StringToWordVector();
        filter.setInputFormat(data);
        filter.setWordsToKeep(1000);
        filter.setIDFTransform(true);
        filter.setOutputWordCounts(true);
        Instances dataFiltered = Filter.useFilter(data, filter);

        SimpleKMeans skm = new SimpleKMeans();
        skm.setDisplayStdDevs(false);
        skm.setDistanceFunction(new EuclideanDistance());
        skm.setMaxIterations(500);
        skm.setDontReplaceMissingValues(true);
        skm.setNumClusters(3);
        skm.setPreserveInstancesOrder(false);
        skm.setSeed(100);

        skm.buildClusterer(dataFiltered);
        ClusterEvaluation eval = new ClusterEvaluation();
        eval.setClusterer(skm);
        eval.evaluateClusterer(dataFiltered);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < dataFiltered.numInstances(); i++) {
            builder.append("用户ID：" + testInfos[i].getUid() + "，提交时间：" + testInfos[i].getSubmitTime() + "，在聚类编号 " + skm.clusterInstance(dataFiltered.instance(i)) + " 中。\n");
        }
        return builder.toString();
    }

}
