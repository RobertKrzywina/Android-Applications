using System;
using System.Drawing;
using System.Windows.Forms;

namespace MixedImages
{
    public partial class Form1 : Form
    {
        private Bitmap bm1;
        private Bitmap bm2;
        private Bitmap mixedBm;

        public Form1()
        {
            InitializeComponent();
            SetCenterScreen();
            InitializeComboBox();
        }

        private void SetCenterScreen()
        {
            StartPosition = FormStartPosition.CenterScreen;
        }

        private void InitializeComboBox()
        {
            comboBox.Items.Add("Suma");
            comboBox.Items.Add("Odejmowanie");
            comboBox.Items.Add("Różnica");
            comboBox.Items.Add("Przyciemnianie");
            comboBox.Items.Add("Rozjaśnianie");
            comboBox.Items.Add("Negacja");
            comboBox.Items.Add("Ciemniejsze");
            comboBox.Items.Add("Jaśniejsze");
            comboBox.Items.Add("Wyłączenie");
            comboBox.Items.Add("Nakładka");
            comboBox.Items.Add("Ostre światło");
            comboBox.Items.Add("Łagodne światło");
            comboBox.Items.Add("Rozcieńczenie");
            comboBox.Items.Add("Wypalanie");
            comboBox.Items.Add("Reflect mode");
            comboBox.Items.Add("Przezroczystosc");
        }

        private void button1_Click(object sender, EventArgs e)
        {
            OpenFileDialog file = new OpenFileDialog();
            file.Filter = "Image Files(*.jpg; *.jpeg; *.gif; *.bmp)|*.jpg; *.jpeg; *.gif; *.bmp";
            if (file.ShowDialog() == DialogResult.OK)
            {
                bm1 = new Bitmap(file.FileName);
                pictureBox1.Image = bm1;
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            OpenFileDialog file = new OpenFileDialog();
            file.Filter = "Image Files(*.jpg; *.jpeg; *.gif; *.bmp)|*.jpg; *.jpeg; *.gif; *.bmp";
            if (file.ShowDialog() == DialogResult.OK)
            {
                bm2 = new Bitmap(file.FileName);
                pictureBox2.Image = bm2;
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            if (null != bm1 && null != bm2 && -1 != comboBox.SelectedIndex)
            {
                MixImages();
            }
            else
            {
                MessageBox.Show("Load first and second img then chose method of mix!");
            }
        }

        private void MixImages()
        {
            int width = pictureBox1.Image.Width;
            int height = pictureBox1.Image.Height;
            int width2 = pictureBox2.Image.Width;
            int height2 = pictureBox2.Image.Height;
            int height3;
            int width3;
            mixedBm = bm1;

            if (width > width2)
            {
                width3 = width2;
            }
            else
            {
                width3 = width;
            }

            if (height > height2)
            {
                height3 = height2;
            }
            else
            {
                height3 = height;
            }

            for (int y = 0; y < height; y++)
            {
                for (int x = 0; x < width; x++)
                {
                    Color p1 = bm1.GetPixel(x, y);
                    int a1 = p1.A;
                    int r1 = p1.R;
                    int g1 = p1.G;
                    int b1 = p1.B;

                    Color p2 = bm1.GetPixel(x, y);
                    int a2 = p2.A;
                    int r2 = p2.R;
                    int g2 = p2.G;
                    int b2 = p2.B;

                    switch (comboBox.SelectedIndex + 1)
                    {
                        case 1:
                            {
                                int r3 = r1 + r2;
                                int g3 = g1 + g2;
                                int b3 = b1 + b2;

                                if (r3 > 255)
                                {
                                    r3 = 255;
                                }
                                if (r3 < 0)
                                {
                                    r3 = 0;
                                }
                                if (g3 > 255)
                                {
                                    g3 = 255;
                                }
                                if (g3 < 0)
                                {
                                    g3 = 0;
                                }
                                if (b3 > 255)
                                {
                                    b3 = 255;
                                }
                                if (b3 < 0)
                                {
                                    b3 = 0;
                                }

                                mixedBm.SetPixel(x, y, Color.FromArgb(a1, r3, g3, b3));
                                break;
                            }
                        case 2:
                            {
                                int r3 = r1 + r2 - 255;
                                int g3 = g1 + g2 - 255;
                                int b3 = b1 + b2 - 255;

                                if (r3 > 255)
                                {
                                    r3 = 255;
                                }
                                if (r3 < 0)
                                {
                                    r3 = 0;
                                }
                                if (g3 > 255)
                                {
                                    g3 = 255;
                                }
                                if (g3 < 0)
                                {
                                    g3 = 0;
                                }
                                if (b3 > 255)
                                {
                                    b3 = 255;
                                }
                                if (b3 < 0)
                                {
                                    b3 = 0;
                                }

                                mixedBm.SetPixel(x, y, Color.FromArgb(a1, r3, g3, b3));
                                break;
                            }
                        case 3:
                            {
                                int r3 = Math.Abs(r2 - r1);
                                int g3 = Math.Abs(g2 - g1);
                                int b3 = Math.Abs(b2 - b1);

                                mixedBm.SetPixel(x, y, Color.FromArgb(a1, r3, g3, b3));
                                break;
                            }
                        case 4:
                            {
                                int r3 = r2 * r1;
                                int g3 = g2 * g1;
                                int b3 = b1 * b2;

                                if (r3 > 255)
                                {
                                    r3 = 255;
                                }
                                if (r3 < 0)
                                {
                                    r3 = 0;
                                }
                                if (g3 > 255)
                                {
                                    g3 = 255;
                                }
                                if (g3 < 0)
                                {
                                    g3 = 0;
                                }
                                if (b3 > 255)
                                {
                                    b3 = 255;
                                }
                                if (b3 < 0)
                                {
                                    b3 = 0;
                                }

                                mixedBm.SetPixel(x, y, Color.FromArgb(a1, r3, g3, b3));
                                break;
                            }
                        case 5:
                            {
                                int r3 = 255 - (255 - r1) * (255 - r2);
                                int g3 = 255 - (255 - g1) * (255 - g2);
                                int b3 = 255 - (255 - b1) * (255 - b2);

                                if (r3 > 255)
                                {
                                    r3 = 255;
                                }
                                if (r3 < 0)
                                {
                                    r3 = 0;
                                }
                                if (g3 > 255)
                                {
                                    g3 = 255;
                                }
                                if (g3 < 0)
                                {
                                    g3 = 0;
                                }
                                if (b3 > 255)
                                {
                                    b3 = 255;
                                }
                                if (b3 < 0)
                                {
                                    b3 = 0;
                                }

                                mixedBm.SetPixel(x, y, Color.FromArgb(a1, r3, g3, b3));
                                break;
                            }
                        case 6:
                            {
                                int r3 = 255 - (Math.Abs(255 - r1 - r2));
                                int b3 = 255 - (Math.Abs(255 - b1 - b2));
                                int g3 = 255 - (Math.Abs(255 - g1 - g2));

                                if (r3 > 255)
                                {
                                    r3 = 255;
                                }
                                if (r3 < 0)
                                {
                                    r3 = 0;
                                }
                                if (g3 > 255)
                                {
                                    g3 = 255;
                                }
                                if (g3 < 0)
                                {
                                    g3 = 0;
                                }
                                if (b3 > 255)
                                {
                                    b3 = 255;
                                }
                                if (b3 < 0)
                                {
                                    b3 = 0;
                                }

                                mixedBm.SetPixel(x, y, Color.FromArgb(a1, r3, g3, b3));
                                break;
                            }
                        case 7:
                            {
                                int r3;
                                int g3;
                                int b3;

                                if (r1 > r2)
                                {
                                    r3 = r1;
                                }
                                else
                                {
                                    r3 = r2;
                                }

                                if (b2 > b1)
                                {
                                    b3 = b2;
                                }
                                else
                                {
                                    b3 = b1;
                                }

                                if (g2 > g1)
                                {
                                    g3 = g2;
                                }
                                else
                                {
                                    g3 = g1;
                                }

                                mixedBm.SetPixel(x, y, Color.FromArgb(a1, r3, g3, b3));
                                break;
                            }
                        case 8:
                            {
                                int r3;
                                int g3;
                                int b3;

                                if (r1 > r2)
                                {
                                    r3 = r2;
                                }
                                else
                                {
                                    r3 = r1;
                                }

                                if (b2 > b1)
                                {
                                    b3 = b1;
                                }
                                else
                                {
                                    b3 = b2;
                                }

                                if (g2 > g1)
                                {
                                    g3 = g1;
                                }
                                else
                                {
                                    g3 = g2;
                                }

                                mixedBm.SetPixel(x, y, Color.FromArgb(a1, r3, g3, b3));
                                break;
                            }
                        case 9:
                            {
                                int r3 = r1 + r2 - 2 * (r1 * r2);
                                int g3 = g1 + g2 - 2 * (g1 * g2);
                                int b3 = b1 + b2 - 2 * (b1 * b2);

                                if (r3 > 255)
                                {
                                    r3 = 255;
                                }
                                if (r3 < 0)
                                {
                                    r3 = 0;
                                }
                                if (g3 > 255)
                                {
                                    g3 = 255;
                                }
                                if (g3 < 0)
                                {
                                    g3 = 0;
                                }
                                if (b3 > 255)
                                {
                                    b3 = 255;
                                }
                                if (b3 < 0)
                                {
                                    b3 = 0;
                                }

                                mixedBm.SetPixel(x, y, Color.FromArgb(a1, r3, g3, b3));
                                break;
                            }
                        case 10:
                            {
                                int r3;
                                int b3;
                                int g3;
                                if (r1 < 128)
                                {
                                    r3 = 2 * r1 * r2;
                                }
                                else
                                {
                                    r3 = 1 - 2 * (1 - r1) * (1 - r2);
                                }

                                if (g1 < 128)
                                {
                                    g3 = 2 * g1 * g2;
                                }
                                else
                                {
                                    g3 = 1 - 2 * (1 - g1) * (1 - g2);
                                }

                                if (b1 < 128)
                                {
                                    b3 = 2 * b1 * b2;
                                }
                                else
                                {
                                    b3 = 1 - 2 * (1 - b1) * (1 - b2);
                                }

                                if (r3 > 255)
                                {
                                    r3 = 255;
                                }
                                if (r3 < 0)
                                {
                                    r3 = 0;
                                }
                                if (g3 > 255)
                                {
                                    g3 = 255;
                                }
                                if (g3 < 0)
                                {
                                    g3 = 0;
                                }
                                if (b3 > 255)
                                {
                                    b3 = 255;
                                }
                                if (b3 < 0)
                                {
                                    b3 = 0;
                                }

                                mixedBm.SetPixel(x, y, Color.FromArgb(a1, r3, g3, b3));
                                break;
                            }
                        case 11:
                            {
                                int r3;
                                int b3;
                                int g3;

                                if (r2 < 0.0)
                                {
                                    r3 = 2 * r1 * r2;
                                }
                                else
                                {
                                    r3 = 1 - 2 * (1 - r1) * (1 - r2);
                                }

                                if (g2 < 0.0)
                                {
                                    g3 = 2 * g1 * g2;
                                }
                                else
                                {
                                    g3 = 1 - 2 * (1 - g1) * (1 - g2);
                                }

                                if (b2 < 0.0)
                                {
                                    b3 = 2 * b1 * b2;
                                }
                                else
                                {
                                    b3 = 1 - 2 * (1 - b1) * (1 - b2);
                                }

                                if (r3 > 255)
                                {
                                    r3 = 255;
                                }
                                if (r3 < 0)
                                {
                                    r3 = 0;
                                }
                                if (g3 > 255)
                                {
                                    g3 = 255;
                                }
                                if (g3 < 0)
                                {
                                    g3 = 0;
                                }
                                if (b3 > 255)
                                {
                                    b3 = 255;
                                }
                                if (b3 < 0)
                                {
                                    b3 = 0;
                                }

                                mixedBm.SetPixel(x, y, Color.FromArgb(a1, r3, g3, b3));
                                break;
                            }
                        case 12:
                            {
                                double r3;
                                double b3;
                                double g3;

                                if (r2 < 0.5)
                                {
                                    r3 = 2 * r1 * r2 + r1 * r1 * (1 - 2 * r2);
                                }
                                else
                                {
                                    r3 = Math.Sqrt(r1) * (2 * r2 - 1) + (2 * r1) * (1 - r2);
                                }

                                if (g2 < 0.5)
                                {
                                    g3 = 2 * g1 * g2 + g1 * g1 * (1 - 2 * g2);
                                }
                                else
                                {
                                    g3 = Math.Sqrt(g1) * (2 * g2 - 1) + (2 * g1) * (1 - g2);
                                }

                                if (b2 < 0.5)
                                {
                                    b3 = 2 * b1 * b2 + b1 * b1 * (1 - 2 * b2);
                                }
                                else
                                {
                                    b3 = Math.Sqrt(b1) * (2 * b2 - 1) + (2 * b1) * (1 - b2);
                                }

                                r3 = (int) r3;
                                g3 = (int) g3;
                                b3 = (int) b3;

                                if (r3 > 255)
                                {
                                    r3 = 255;
                                }
                                if (r3 < 0)
                                {
                                    r3 = 0;
                                }
                                if (g3 > 255)
                                {
                                    g3 = 255;
                                }
                                if (g3 < 0)
                                {
                                    g3 = 0;
                                }
                                if (b3 > 255)
                                {
                                    b3 = 255;
                                }
                                if (b3 < 0)
                                {
                                    b3 = 0;
                                }

                                mixedBm.SetPixel(x, y, Color.FromArgb(a1, (int) r3, (int) g3, (int) b3));
                                break;
                            }
                        case 13:
                            {
                                int r3 = r1 / (1 - r2);
                                int g3 = g1 / (1 - g2);
                                int b3 = b1 / (1 - b2);

                                if (r3 > 255)
                                {
                                    r3 = 255;
                                }
                                if (r3 < 0)
                                {
                                    r3 = 0;
                                }
                                if (g3 > 255)
                                {
                                    g3 = 255;
                                }
                                if (g3 < 0)
                                {
                                    g3 = 0;
                                }
                                if (b3 > 255)
                                {
                                    b3 = 255;
                                }
                                if (b3 < 0)
                                {
                                    b3 = 0;
                                }

                                mixedBm.SetPixel(x, y, Color.FromArgb(a1, (int) r3, (int) g3, (int) b3));
                                break;
                            }

                        case 14:
                            {
                                int r3 = 1 - (1 - r1) / r2;
                                int b3 = 1 - (1 - b1) / b2;
                                int g3 = 1 - (1 - g1) / g2;

                                if (r3 > 255)
                                {
                                    r3 = 255;
                                }
                                if (r3 < 0)
                                {
                                    r3 = 0;
                                }
                                if (g3 > 255)
                                {
                                    g3 = 255;
                                }
                                if (g3 < 0)
                                {
                                    g3 = 0;
                                }
                                if (b3 > 255)
                                {
                                    b3 = 255;
                                }
                                if (b3 < 0)
                                {
                                    b3 = 0;
                                }

                                mixedBm.SetPixel(x, y, Color.FromArgb(a1, (int) r3, (int) g3, (int) b3));
                                break;
                            }

                        case 15:
                            {
                                int r3 = (r1 * r1) / (1 - r2);
                                int g3 = (g1 * g1) / (1 - g2);
                                int b3 = (b1 * b1) / (1 - b2);

                                if (r3 > 255)
                                {
                                    r3 = 255;
                                }
                                if (r3 < 0)
                                {
                                    r3 = 0;
                                }
                                if (g3 > 255)
                                {
                                    g3 = 255;
                                }
                                if (g3 < 0)
                                {
                                    g3 = 0;
                                }
                                if (b3 > 255)
                                {
                                    b3 = 255;
                                }
                                if (b3 < 0)
                                {
                                    b3 = 0;
                                }

                                mixedBm.SetPixel(x, y, Color.FromArgb(a1, (int) r3, (int) g3, (int) b3));
                                break;
                            }
                        case 16:
                            {
                                int r3 = (1 - 1) * r2 + 1 * r1;
                                int g3 = (1 - 1) * g2 + 1 * g1;
                                int b3 = (1 - 1) * b2 + 1 * b1;

                                if (r3 > 255)
                                {
                                    r3 = 255;
                                }
                                if (r3 < 0)
                                {
                                    r3 = 0;
                                }
                                if (g3 > 255)
                                {
                                    g3 = 255;
                                }
                                if (g3 < 0)
                                {
                                    g3 = 0;
                                }
                                if (b3 > 255)
                                {
                                    b3 = 255;
                                }
                                if (b3 < 0)
                                {
                                    b3 = 0;
                                }

                                mixedBm.SetPixel(x, y, Color.FromArgb(a1, (int) r3, (int) g3, (int) b3));
                                break;
                            }
                    }
                }
            }
            pictureBox3.Image = mixedBm;
        }
    }
}
