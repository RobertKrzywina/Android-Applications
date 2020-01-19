using System;
using System.Drawing;
using System.Windows.Forms;

namespace Smudge_Sharpen_FindingEdges
{
    public partial class Form1 : Form
    {
        private Bitmap mainMap;
        private Bitmap bm1;
        private Bitmap bm2;
        private Bitmap bm3;

        public Form1()
        {
            InitializeComponent();
            SetCenterScreen();
        }

        private void SetCenterScreen()
        {
            StartPosition = FormStartPosition.CenterScreen;
        }

        private void button_Click_1(object sender, EventArgs e)
        {
            OpenFileDialog file = new OpenFileDialog();
            file.Filter = "Image Files(*.jpg; *.jpeg; *.png; *.gif; *.bmp)|*.jpg; *.jpeg; *.png; *.gif; *.bmp";
            if (file.ShowDialog() == DialogResult.OK)
            {
                mainMap = new Bitmap(file.FileName);
                bm1 = new Bitmap(Image.FromFile(file.FileName));
                bm2 = new Bitmap(Image.FromFile(file.FileName));
                bm3 = new Bitmap(Image.FromFile(file.FileName));

                int width = mainMap.Width;
                int height = mainMap.Height;
                for (int i=1; i<height-1; i++)
                {
                    for (int j=1; j<width-1; j++)
                    {
                        Color c1 = mainMap.GetPixel(j, i);
                        Color c2 = mainMap.GetPixel(j - 1, i);
                        Color c3 = mainMap.GetPixel(j + 1, i);
                        Color c4 = mainMap.GetPixel(j - 1, i - 1);
                        Color c5 = mainMap.GetPixel(j - 1, i + 1);
                        Color c6 = mainMap.GetPixel(j, i + 1);
                        Color c7 = mainMap.GetPixel(j, i - 1);
                        Color c8 = mainMap.GetPixel(j + 1, i + 1);
                        Color c9 = mainMap.GetPixel(j + 1, i - 1);
                        int sR = ((c1.R + c2.R + c3.R + c4.R + c5.R + c6.R + c7.R + c8.R + c9.R) / 9);
                        int sG = ((c1.G + c2.G + c3.G + c4.G + c5.G + c6.G + c7.G + c8.G + c9.G) / 9);
                        int sB = ((c1.B + c2.B + c3.B + c4.B + c5.B + c6.B + c7.B + c8.B + c9.B) / 9);

                        c1 = mainMap.GetPixel(j, i - 1);
                        c2 = mainMap.GetPixel(j - 1, i);
                        c3 = mainMap.GetPixel(j, i);
                        c4 = mainMap.GetPixel(j + 1, i);
                        c5 = mainMap.GetPixel(j, i + 1);
                        int r = c1.R + c2.R + c3.R * (-4) + c4.R + c5.R;
                        int g = c1.G + c2.G + c3.G * (-4) + c4.G + c5.G;
                        int b = c1.B + c2.B + c3.B * (-4) + c4.B + c5.B;

                        int avg = (r + g + b) / 3;
                        if (avg > 255) avg = 255;
                        if (avg < 0) avg = 0;

                        bm1.SetPixel(j, i, Color.FromArgb(avg, avg, avg));
                        bm3.SetPixel(j, i, Color.FromArgb(sR, sG, sB));

                        smudgeBox.Image = bm3;
                        sharpenBox.Image = bm1;
                        findingEdgesBox.Image = bm2;
                    }
                }
            }
        }
    }
}
