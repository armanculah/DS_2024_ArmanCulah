/*package homework3;

import java.io.FileWriter;
import java.io.IOException;

public class inorderTraversal {

    public void inorderTraversalAndSaveToFile(String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        inorderTraversalAndSaveToFile(root, writer);
        writer.close();
    }

    private void inorderTraversalAndSaveToFile(Node node, FileWriter writer) throws IOException {
        if (node == null) return;
        inorderTraversalAndSaveToFile(node.left, writer);
        for (Entry entry : node.values) {
            writer.write(entry.name() + ";" +
                    entry.streetAddress() + ";" +
                    entry.city() + ";" +
                    entry.postcode() + ";" +
                    entry.country() + ";" +
                    entry.phoneNumber() + "\n");
        }
        inorderTraversalAndSaveToFile(node.right, writer);
    }
}
}
*/