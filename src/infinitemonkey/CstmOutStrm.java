package infinitemonkey;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

public class CstmOutStrm extends OutputStream {
    private JTextArea textArea;

    public CstmOutStrm(JTextArea textArea)
    {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException
    {
        textArea.append(String.valueOf((char)b));
        textArea.setCaretPosition(textArea.getDocument().getLength());
       // textArea.update(textArea.getGraphics());
    }
}
