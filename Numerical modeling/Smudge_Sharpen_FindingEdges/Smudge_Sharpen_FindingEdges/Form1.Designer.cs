namespace Smudge_Sharpen_FindingEdges
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.button = new System.Windows.Forms.Button();
            this.smudgeBox = new System.Windows.Forms.PictureBox();
            this.sharpenBox = new System.Windows.Forms.PictureBox();
            this.findingEdgesBox = new System.Windows.Forms.PictureBox();
            ((System.ComponentModel.ISupportInitialize)(this.smudgeBox)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.sharpenBox)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.findingEdgesBox)).BeginInit();
            this.SuspendLayout();
            // 
            // button
            // 
            this.button.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.button.Location = new System.Drawing.Point(242, 34);
            this.button.Name = "button";
            this.button.Size = new System.Drawing.Size(224, 40);
            this.button.TabIndex = 0;
            this.button.Text = "Load";
            this.button.UseVisualStyleBackColor = true;
            this.button.Click += new System.EventHandler(this.button_Click_1);
            // 
            // smudgeBox
            // 
            this.smudgeBox.Location = new System.Drawing.Point(12, 127);
            this.smudgeBox.Name = "smudgeBox";
            this.smudgeBox.Size = new System.Drawing.Size(224, 149);
            this.smudgeBox.TabIndex = 1;
            this.smudgeBox.TabStop = false;
            // 
            // sharpenBox
            // 
            this.sharpenBox.Location = new System.Drawing.Point(242, 127);
            this.sharpenBox.Name = "sharpenBox";
            this.sharpenBox.Size = new System.Drawing.Size(224, 149);
            this.sharpenBox.TabIndex = 2;
            this.sharpenBox.TabStop = false;
            // 
            // findingEdgesBox
            // 
            this.findingEdgesBox.Location = new System.Drawing.Point(472, 127);
            this.findingEdgesBox.Name = "findingEdgesBox";
            this.findingEdgesBox.Size = new System.Drawing.Size(224, 149);
            this.findingEdgesBox.TabIndex = 3;
            this.findingEdgesBox.TabStop = false;
            // 
            // Form1
            // 
            this.ClientSize = new System.Drawing.Size(714, 288);
            this.Controls.Add(this.findingEdgesBox);
            this.Controls.Add(this.sharpenBox);
            this.Controls.Add(this.smudgeBox);
            this.Controls.Add(this.button);
            this.Name = "Form1";
            ((System.ComponentModel.ISupportInitialize)(this.smudgeBox)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.sharpenBox)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.findingEdgesBox)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.Button button;
        private System.Windows.Forms.PictureBox smudgeBox;
        private System.Windows.Forms.PictureBox sharpenBox;
        private System.Windows.Forms.PictureBox findingEdgesBox;
    }
}

