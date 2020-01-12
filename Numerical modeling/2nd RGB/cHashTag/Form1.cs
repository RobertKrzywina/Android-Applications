using System;
using System.Drawing;
using System.Windows.Forms;

namespace cHashTag
{
    public partial class Form1 : Form
    {
        private Bitmap mainMap;
        private Bitmap trackBarMap;

        public Form1()
        {
            InitializeComponent();
            setComponentsVisibility(false);
        }

        private void setComponentsVisibility(bool b)
        {
            redLabel.Visible = b;
            greenLabel.Visible = b;
            blueLabel.Visible = b;
            originalImgLabel.Visible = b;
            stGrayLabel.Visible = b;
            ndGrayLabel.Visible = b;
            trackBar.Visible = b;
            trackBarValueLabel.Visible = b;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            OpenFileDialog file = new OpenFileDialog();
            file.Filter = "Image Files(*.jpg; *.jpeg; *.gif; *.bmp)|*.jpg; *.jpeg; *.gif; *.bmp";
            if (file.ShowDialog() == DialogResult.OK)
            {
                setComponentsVisibility(true);
                mainMap = new Bitmap(file.FileName);

                Bitmap map1 = new Bitmap(mainMap);
                Bitmap map2 = new Bitmap(mainMap);
                Bitmap map3 = new Bitmap(mainMap);
                Bitmap map4 = new Bitmap(mainMap);
                Bitmap map5 = new Bitmap(mainMap);
                Bitmap map6 = new Bitmap(mainMap);
                trackBarMap = new Bitmap(mainMap);

                int width = mainMap.Width;
                int height = mainMap.Height;
                for (int i = 0; i < width; i++)
                {
                    for (int j = 0; j < height; j++)
                    {
                        Color color = mainMap.GetPixel(i, j);

                        int r = color.R;
                        int g = color.G;
                        int b = color.B;

                        map1.SetPixel(i, j, Color.FromArgb(r, g, b));
                        map2.SetPixel(i, j, Color.FromArgb(r, 0, 0));
                        map3.SetPixel(i, j, Color.FromArgb(0, g, 0));
                        map4.SetPixel(i, j, Color.FromArgb(0, 0, b));

                        int stGray = calculateStGrayColor(r, g, b);
                        map5.SetPixel(i, j, Color.FromArgb(stGray, stGray, stGray));

                        int ndGray = calculateNdGrayColor(r, g, b);
                        map6.SetPixel(i, j, Color.FromArgb(ndGray, ndGray, ndGray));
                    }
                }
                pictureBox2.Image = map2;
                pictureBox3.Image = map3;
                pictureBox4.Image = map4;
                pictureBox5.Image = map5;
                pictureBox6.Image = map6;
                pictureBox7.Image = map1;
            }
        }

        private void trackBar1_Scroll(object sender, EventArgs e)
        {
            trackBarValueLabel.Text = "" + trackBar.Value;
            if (null != trackBarMap)
            {
                int width = mainMap.Width;
                int height = mainMap.Height;
                for (int i = 0; i < width; i++)
                {
                    for (int j = 0; j < height; j++)
                    {
                        Color color = mainMap.GetPixel(i, j);

                        int r = color.R;
                        int g = color.G;
                        int b = color.B;

                        int gray = calculateStGrayColor(r, g, b);
                        trackBarMap.SetPixel(i, j, Color.FromArgb(trackBar.Value, gray, gray, gray));
                    }
                }
                pictureBox1.Image = trackBarMap;
            }
        }

        private int calculateStGrayColor(int r, int g, int b)
        {
            return (int)((r * 0.3) + (g * 0.59) + (b * 0.11));
        }

        private int calculateNdGrayColor(int r, int g, int b)
        {
            return (r + g + b) / 3;
        }
    }
}
