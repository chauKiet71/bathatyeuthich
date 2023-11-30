package main;

import KhamPha_BaiHat.BaiHat;
import KhamPha_NgheSi.NgheSi;
import KhamPha_PlayList.PlayList;
import dao.Playlist;
import Menu.MenuEvent;
import NgheGiHomNay.Top100;
import NgheGiHomNay.TuyenTap;
import NhacCuaToi.BaiHatYeuThich;
import NhacCuaToi.DaTaiLen;
import NhacCuaToi.NgheGanDay;
import NhacCuaToi.TaoPlaylist;
import TimKiem.FormTimKiem;
import TimKiem.FormTimKiem2;
import TimKiem.TimKiem_TatCa;
import dao.BaiHatDAO;
import dao.BaiHatYeuThichDAO;
import entity.AccountData;
import entity.BaiHatEntity;
import entity.BaiHatEntityTest;
import form.ChillForm;
import form.FormChiTietPlaylist;
import form.FormDetailMusic;
import form.FormTimKiemItem;
import saveEvent.BaiHatStateManager;
import form.FormTrangChu;
import static form.FormTrangChu.PLAYLIST_SELECTED_EVENT;
import form.Form_ChuDe;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import swing.ScrollBarr;
import utils.EventManager;
import javazoom.jl.player.Player;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.tag.TagException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javazoom.jl.decoder.JavaLayerException;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import saveEvent.PlaylistSelected;
import saveEvent.PlaylistSelectedCopy;
import saveEvent.SearchStateManager;

public class Main extends javax.swing.JFrame {

    private Player player;
    private long totalLength;
    private long pause;
    private FileInputStream fis;
//    private File myfile = null;
    private BufferedInputStream bis;
    private Timer timer;

    private boolean isPlaying = false;
    private boolean isPaused;
    private boolean isTim = false;
    int i = 0;

    private Playlist playlist = new Playlist();

    public TimKiem_TatCa songpanel;

    BaiHatStateManager manager = new BaiHatStateManager();
    SearchStateManager searched = new SearchStateManager();
    BaiHatDAO bhDao = new BaiHatDAO();

    BaiHatYeuThichDAO bhyt = new BaiHatYeuThichDAO();
    private static PlaylistSelectedCopy cc;
    public static final String PLAYLIST_SELECTED_EVENT2 = "PLAYLIST_SELECTED_EVENT2";

    public Main() {
        initComponents();
        content.setVerticalScrollBar(new ScrollBarr());
        menu1.setEvent(new MenuEvent() {
            @Override
            public void selected(int index, int subIndex) {
                if (index == 0) {
                    FormTimKiem2 wel = new FormTimKiem2();
                    setPanel(wel);
                    lbTitle.setText("Tìm kiếm");
                } else if (index == 1) {
                    FormTrangChu wel = new FormTrangChu();
                    setPanel(wel);
                    lbTitle.setText("Trang Chủ");
                } else if (index == 2 && subIndex == 1) {
                    BaiHat wel = new BaiHat();
                    setPanel(wel);
                    lbTitle.setText("Bài Hát");
                } else if (index == 2 && subIndex == 2) {
                    PlayList wel = new PlayList();
                    setPanel(wel);
                    lbTitle.setText("Playlist");
                } else if (index == 2 && subIndex == 4) {
                    NgheSi wel = new NgheSi();
                    setPanel(wel);
                    lbTitle.setText("Nghệ Sỹ");
                } else if (index == 3 && subIndex == 1) {
                    Form_ChuDe wel = new Form_ChuDe();
                    setPanel(wel);
                    lbTitle.setText("Chủ Đề");
                } else if (index == 3 && subIndex == 2) {
                    TuyenTap wel = new TuyenTap();
                    setPanel(wel);
                    lbTitle.setText("Tuyển Tập");
                } else if (index == 3 && subIndex == 3) {
                    Top100 wel = new Top100();
                    setPanel(wel);
                    lbTitle.setText("Top 100");
                } else if (index == 4 && subIndex == 1) {
                    NgheGanDay wel = new NgheGanDay();
                    setPanel(wel);
                    lbTitle.setText("Nghe Gần Đây");
                } else if (index == 4 && subIndex == 2) {
                    BaiHatYeuThich wel = new BaiHatYeuThich();
                    setPanel(wel);
                    lbTitle.setText("Bài Hát Yêu Thích");
                } else if (index == 4 && subIndex == 3) {
                    TaoPlaylist wel = new TaoPlaylist();
                    setPanel(wel);
                    lbTitle.setText("Playlist");
                } else if (index == 4 && subIndex == 4) {
                    DaTaiLen wel = new DaTaiLen();
                    setPanel(wel);
                    lbTitle.setText("Đã Tải Lên");
                } else {
                    JOptionPane.showMessageDialog(null, index + " " + subIndex);
                }
            }
        }
        );

        init();
        initMusic();

        EventManager.addListener(FormTrangChu.BAI_HAT_SELECTED_EVENT, (event, data) -> {
            if (data instanceof BaiHatEntity) {
                BaiHatEntity selectedBaiHat = (BaiHatEntity) data;
                manager.setSelectedBaiHat(selectedBaiHat);
                playlist.addSong(selectedBaiHat);
                try {
                    fillMusic(selectedBaiHat);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        EventManager.addListener(FormTrangChu.PLAYLIST_SELECTED_EVENT, (event, data) -> {
            if (data instanceof String) {
                String ok = (String) data;
                cc = new PlaylistSelectedCopy();
                cc.setPlaylistSelectedCopy(ok);
//                JOptionPane.showMessageDialog(null, cc.getPlaylistSelectedCopy());
                EventQueue.invokeLater(() -> {
                    EventManager.fireEvent(PLAYLIST_SELECTED_EVENT2, cc.getPlaylistSelectedCopy());
                });
                FormChiTietPlaylist wel = new FormChiTietPlaylist();
                setPanel(wel);
            }
        });

        playlist.printSongs();

    }

//    thêm các Jpanel Form vào Main chính
    public void setPanel(JComponent panel) {
        panel.setSize(926, 3000);
        panel.setLocation(0, 0);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        content.setViewportView(scrollPane);
    }

    public void init() {
        btnTim.setToolTipText("Yêu thích");
        btnNext.setToolTipText("Phát tiếp theo");
        btnPrev.setToolTipText("Phát bài đã nghe");
        btnDetails.setToolTipText("Lyric");
        btnAddPlaylist.setToolTipText("Thêm vào Playlist");
        btnDownload.setToolTipText("Tải xuống");
        btnListMusic.setToolTipText("Danh sách phát");
        btnAgain.setToolTipText("Nghe lại");
        setToolTip(btnTim, "Yêu thích");
        setToolTip(btnNext, "Phát tiếp theo");
        setToolTip(btnPrev, "Phát bài đã nghe");
        setToolTip(btnDetails, "Lyric");
        setToolTip(btnAddPlaylist, "Thêm vào Playlist");
        setToolTip(btnDownload, "Tải xuống");
        setToolTip(btnListMusic, "Danh sách phát");
        setToolTip(btnAgain, "Nghe lại");

        hoverBtn(btnPlay);
        hoverBtn(btnDetails);
        hoverBtn(btnPrev);
        hoverBtn(btnNext);
        hoverBtn(btnDownload);
        hoverBtn(btnTim);
        hoverBtn(btnAgain);
    }

//    thêm ảnh và các thông tin mặc định cho from phát nhạc
    public void initMusic() {
        try {
            BufferedImage originalImage = ImageIO.read(getClass().getResource("/icon/Chill/default.jpg"));

            int width = lbanh.getWidth();
            int height = lbanh.getHeight();

            // Tạo ảnh mới với đường viền bo góc và kích thước vừa với lbanh
            BufferedImage roundedImage = createRoundedImage(originalImage, width, height);

            // Tạo ImageIcon từ ảnh mới và thiết lập cho lbanh
            ImageIcon roundedImageIcon = new ImageIcon(roundedImage);
            lbanh.setIcon(roundedImageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lbName.setText("Thưởng thức những giai điệu");
        lbName2.setText("Theo cách riêng của bạn");
        lbTime.setText("00:00");
    }

    public void hoverBtn(JButton btn) {
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(33, 42, 53));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(29, 38, 49));

            }
        });
    }

//    khi một bài hát trong bảng được chọn sẽ gửi sự kiện đến main và gọi hàm fillMusic để đặc các thông tin liên quan đến bài hát vào form phát nhạc và phát nhạc
    public void fillMusic(BaiHatEntity selectedBaiHat) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
        isPlaying = false;
        lbName.setText(selectedBaiHat.getTenBh());
        lbName2.setText(selectedBaiHat.getCaSi());
        lbTime.setText(selectedBaiHat.getThoiGian());
        btnPlay.setIcon(new ImageIcon(getClass().getResource("/icon/pause48.png")));

        try {
            BufferedImage originalImage = ImageIO.read(getClass().getResource(selectedBaiHat.getAnh()));

            int width = lbanh.getWidth();
            int height = lbanh.getHeight();

            // Tạo ảnh mới với đường viền bo góc và kích thước vừa với lbanh
            BufferedImage roundedImage = createRoundedImage(originalImage, width, height);

            // Tạo ImageIcon từ ảnh mới và thiết lập cho lbanh
            ImageIcon roundedImageIcon = new ImageIcon(roundedImage);
            lbanh.setIcon(roundedImageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileString = selectedBaiHat.getAmThanh();
        File file = new File(fileString);

        long durationSeconds = longMusic(file);
        String time = formatDuration(durationSeconds);
        lbTime.setText(time);

        play(file);

    }

//    tạo ảnh bo tròn 4 góc và có border
    private BufferedImage createRoundedImage(BufferedImage originalImage, int width, int height) {
        BufferedImage roundedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = roundedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setClip(new RoundRectangle2D.Float(0, 0, width, height, 20, 20)); // Điều chỉnh bán kính bo góc ở đây
        g2.drawImage(originalImage, 0, 0, width, height, null);

        g2.setColor(new Color(38, 46, 57)); // Màu sắc của đường viền
        g2.setStroke(new BasicStroke(1)); // Độ đậm của đường viền
        g2.drawRoundRect(0, 0, width, height, 20, 20); // Vẽ đường viền

        g2.dispose();

        return roundedImage;
    }

//    hàm play dùng để phát nhạc, đưa vào một đường dẫn tuyệt đối đến bài hát
    private void play(File file) {
        i = 0;
        lbStart.setText("00:00");
        try {
            if (player != null) {
                isPlaying = false; // Dừng phát nhạc
                player.close(); // Giải phóng tài nguyên
            }

            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            player = new Player(bis);
            totalLength = fis.available();
            isPlaying = true; // Bắt đầu phát nhạc

            new Thread() {
                public void run() {
                    try {
                        player.play();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            long durationSeconds = longMusic(file);
            setSlider(durationSeconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    nghe lại nhạc một lần nữa
    public void playAgain() {
        if (manager.getSelectedBaiHat() != null) {
            i = 0;
            lbStart.setText("00:00");
            btnPlay.setIcon(new ImageIcon(getClass().getResource("/icon/pause48.png")));
            try {
                if (player != null) {
                    isPlaying = false; // Dừng phát nhạc
                    player.close(); // Giải phóng tài nguyên
                }

                fis = new FileInputStream(manager.getSelectedBaiHat().getAmThanh());
                bis = new BufferedInputStream(fis);
                player = new Player(bis);
                totalLength = fis.available();
                isPlaying = true; // Bắt đầu phát nhạc

                new Thread() {
                    public void run() {
                        try {
                            player.play();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                String fileString = manager.getSelectedBaiHat().getAmThanh();
                File file = new File(fileString);
                long durationSeconds = longMusic(file);
                setSlider(durationSeconds);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return;
        }

    }

    public void pause() {
        try {
            if (player != null) {
                if (isPaused && i == 1) {
                    // Nếu đang tạm ngừng, thì tiếp tục phát nhạc từ vị trí đã tạm ngưng
                    try {
                        timer.start();
                        File file = new File(manager.getSelectedBaiHat().getAmThanh());
                        fis = new FileInputStream(file);
                        bis = new BufferedInputStream(fis);
                        player = new Player(bis);
                        if (pause > 0) {
                            fis.skip(totalLength - pause);
                        }

                        new Thread() {
                            public void run() {
                                try {
                                    player.play();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                        btnPlay.setIcon(new ImageIcon(getClass().getResource("/icon/pause48.png")));
                        isPaused = false;
                        i = 0;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    // Nếu đang phát, thì tạm ngừng và lưu vị trí tạm ngưng
                    try {
                        i = 1;
                        timer.stop();
                        pause = fis.available();
                        System.out.println(pause);
                        player.close();
                        btnPlay.setIcon(new ImageIcon(getClass().getResource("/icon/play48.png")));
                        isPaused = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    lấy độ dài của file mp3 chuyển thành giây
    public long longMusic(File mp3File) {
        try {
            AudioFile audioFile = AudioFileIO.read(mp3File);
            AudioHeader audioHeader = audioFile.getAudioHeader();
            int durationInSeconds = audioHeader.getTrackLength();
            return durationInSeconds;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

//    lấy độ dài của file mp3 đã chuyển thành giây qua kiểu String mm:ss
    private static String formatDuration(long durationInSeconds) {
        long minutes = durationInSeconds / 60;
        long seconds = durationInSeconds % 60;

        // Format để hiển thị số phút và giây dưới dạng mm:ss
        return String.format("%02d:%02d", minutes, seconds);
    }

    private int currentValue;

    //set thanh Slider thay đổi theo độ dài của Mp3 theo giây
    public void setSlider(long durationLong) {
        // Tạm thời dừng timer nếu đang chạy
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        int durationInt = (int) durationLong;
        slider.setMinimum(0);
        slider.setMaximum(durationInt);
        slider.setValue(0);

        ActionListener timerActionListener = new ActionListener() {
//            int currentValue = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                currentValue++;
                slider.setValue(currentValue);
                String start = formatDuration(currentValue);
                lbStart.setText(start);
                i = 0;
                if (currentValue == durationInt) {
                    timer.stop(); // Dừng timer khi slider đạt giá trị tối đa
                    btnPlay.setIcon(new ImageIcon(getClass().getResource("/icon/play48.png")));
                }
            }
        };

        timer = new Timer(1000, timerActionListener);
        currentValue = 0; // Khởi tạo currentValue

        slider.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                JSlider source = (JSlider) e.getSource();
                int value = source.getValue();

                // Cập nhật giá trị của currentValue, slider và lbStart
                timer.stop();
                timer.setInitialDelay(0);
                timer.setDelay(1000);
                timer.setRepeats(true);
                timer.setCoalesce(true);

                currentValue = value;
                slider.setValue(value);
                String start = formatDuration(value);
                lbStart.setText(start);

                timer.start();

                try {

                    player.close();
                    File file = new File(manager.getSelectedBaiHat().getAmThanh());
                    AudioFile audioFile = AudioFileIO.read(file);
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    player = new Player(bis);

                    int sliderMax = slider.getMaximum();
                    long duration = audioFile.getAudioHeader().getTrackLength();
                    long position = (duration * value) / sliderMax;

                    fis.skip(position * (file.length() / duration));

                    new Thread() {
                        public void run() {
                            try {
                                player.play();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                    btnPlay.setIcon(new ImageIcon(getClass().getResource("/icon/pause48.png")));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Lưu lại timer để có thể dừng nó khi cần thiết
//        this.timer = timer;
        if (durationInt > 0) {
            timer.start();
        }
    }

    private void playPreviousSong() throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
        playlist.playPrevious();
        BaiHatEntity currentSong = playlist.getCurrentSong();
        if (currentSong != null) {
            fillMusic(currentSong);
        }
    }

    private void playNextSong() throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
        playlist.playNext();
        BaiHatEntity currentSong = playlist.getCurrentSong();
        if (currentSong != null) {
            fillMusic(currentSong);
        }
    }

    public BaiHatEntity getEntity1(int index) {
        BaiHatEntity entity = new BaiHatEntity();
        entity.setSoLuotThich(manager.getSelectedBaiHat().getSoLuotThich() + index);
        entity.setMaBh(manager.getSelectedBaiHat().getMaBh());
        return entity;
    }

    public void tim() {
        if (manager.getSelectedBaiHat() != null) {
            if (isTim == false) {
                //tim thich
                btnTim.setIcon(new ImageIcon(getClass().getResource("/icon/heart24.png")));
                BaiHatEntity bhEntity = getEntity1(1);
                BaiHatYeuThichInsert();
                try {
                    bhDao.updateTim(bhEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                isTim = true;
            } else {
                //khong thich
                btnTim.setIcon(new ImageIcon(getClass().getResource("/icon/heart24null.png")));
                BaiHatEntity bhEntity = getEntity1(-1);
                try {
                    bhDao.updateTim(bhEntity);
                    BaiHatYeuThichDelete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                isTim = false;
            }
        } else {
            return;
        }

    }

    public void setToolTip(JButton btn, String text) {
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Hiển thị tooltip khi chuột hover vào nút
                btn.setToolTipText(text);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Xóa tooltip khi chuột rời khỏi nút
                btn.setToolTipText(null);
            }
        });
    }

    //bai hat yeu thich
    entity.BaiHatYeuThich getNgheGanDay(String mabh, String tentk) {
        entity.BaiHatYeuThich bhyt = new entity.BaiHatYeuThich();
        bhyt.setMaBH(mabh);
        bhyt.setTenTK(tentk);
        return bhyt;
    }

    BaiHatYeuThichDAO bhytDao = new BaiHatYeuThichDAO();

    void BaiHatYeuThichInsert() {
        System.out.println("ma bai hat form trang chu" + AccountData.getSavaMaBh());
        entity.BaiHatYeuThich bh = getNgheGanDay(AccountData.getSavaMaBh(), AccountData.getTenTK());
        try {
            bhyt.insert(bh);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void BaiHatYeuThichDelete() {
        System.out.println("ma bai hat form trang chu" + AccountData.getSavaMaBh());
        entity.BaiHatYeuThich bh = getNgheGanDay(AccountData.getSavaMaBh(), AccountData.getTenTK());
        try {
            bhyt.deleteByid(AccountData.getSavaMaBh());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new swing.Panel();
        move = new component.header();
        lbOut = new javax.swing.JLabel();
        lbTitle = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        content = new javax.swing.JScrollPane();
        formTrangChu1 = new form.FormTrangChu();
        jPanel2 = new javax.swing.JPanel();
        account1 = new component.Account();
        jSeparator1 = new javax.swing.JSeparator();
        body = new javax.swing.JPanel();
        menu1 = new Menu.Menu();
        jSeparator2 = new javax.swing.JSeparator();
        panelMusic = new javax.swing.JPanel();
        lbTime = new javax.swing.JLabel();
        slider = new swing.Slider();
        lbStart = new javax.swing.JLabel();
        panelBorder1 = new swing.PanelBorder();
        lbName = new javax.swing.JLabel();
        lbName2 = new javax.swing.JLabel();
        lbanh = new javax.swing.JLabel();
        btnDownload = new swing.Button();
        btnAgain = new swing.Button();
        btnPrev = new swing.Button();
        btnNext = new swing.Button();
        btnPlay = new swing.Button();
        btnDetails = new swing.Button();
        btnTim = new swing.Button();
        btnVolumn = new swing.Button();
        btnListMusic = new swing.Button();
        btnAddPlaylist = new swing.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));
        setUndecorated(true);

        panel1.setBackground(new java.awt.Color(29, 38, 49));
        panel1.setForeground(new java.awt.Color(255, 255, 255));
        panel1.setOpaque(true);

        move.setBackground(new java.awt.Color(29, 38, 49));
        move.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                moveMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                moveMouseMoved(evt);
            }
        });

        lbOut.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbOut.setForeground(new java.awt.Color(255, 0, 0));
        lbOut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbOut.setText("X");
        lbOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbOutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbOutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbOutMouseExited(evt);
            }
        });

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(255, 255, 255));
        lbTitle.setText("Trang Chủ");

        jSeparator3.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator3.setOpaque(true);

        javax.swing.GroupLayout moveLayout = new javax.swing.GroupLayout(move);
        move.setLayout(moveLayout);
        moveLayout.setHorizontalGroup(
            moveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(moveLayout.createSequentialGroup()
                .addGroup(moveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 916, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbOut, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        moveLayout.setVerticalGroup(
            moveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, moveLayout.createSequentialGroup()
                .addGroup(moveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(moveLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbOut))
                    .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        content.setBackground(new java.awt.Color(29, 38, 49));
        content.setBorder(null);
        content.setForeground(new java.awt.Color(255, 255, 255));
        content.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        content.setHorizontalScrollBar(null);
        content.setViewportView(formTrangChu1);

        jPanel2.setBackground(new java.awt.Color(29, 38, 49));

        account1.setBackground(new java.awt.Color(29, 38, 49));
        account1.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator1.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator1.setOpaque(true);

        body.setOpaque(false);

        menu1.setBackground(new java.awt.Color(255, 153, 153));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyLayout.createSequentialGroup()
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(body, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(account1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(account1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );

        jSeparator2.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator2.setOpaque(true);

        panelMusic.setOpaque(false);
        panelMusic.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbTime.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbTime.setForeground(new java.awt.Color(255, 255, 255));
        lbTime.setText("03:21");
        panelMusic.add(lbTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 596, -1, 31));
        panelMusic.add(slider, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 596, 210, 30));

        lbStart.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbStart.setForeground(new java.awt.Color(255, 255, 255));
        lbStart.setText("00:00");
        panelMusic.add(lbStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 596, -1, 30));

        panelBorder1.setBackground(new java.awt.Color(38, 46, 57));

        lbName.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbName.setForeground(new java.awt.Color(255, 255, 255));
        lbName.setText("Lạc Trôi");
        lbName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lbName2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbName2.setForeground(new java.awt.Color(204, 204, 204));
        lbName2.setText("Sơn Tùng M-TP");
        lbName2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lbanh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbName2, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addComponent(lbanh, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lbanh, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbName2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelMusic.add(panelBorder1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, -1));

        btnDownload.setBackground(new java.awt.Color(29, 38, 49));
        btnDownload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/download24.png"))); // NOI18N
        panelMusic.add(btnDownload, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 540, 40, 40));

        btnAgain.setBackground(new java.awt.Color(29, 38, 49));
        btnAgain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/repeat.png"))); // NOI18N
        btnAgain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgainActionPerformed(evt);
            }
        });
        panelMusic.add(btnAgain, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 639, 40, 40));

        btnPrev.setBackground(new java.awt.Color(29, 38, 49));
        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/back.png"))); // NOI18N
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        panelMusic.add(btnPrev, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 639, 40, 40));

        btnNext.setBackground(new java.awt.Color(29, 38, 49));
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/next.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        panelMusic.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 639, 40, 40));

        btnPlay.setBackground(new java.awt.Color(29, 38, 49));
        btnPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/play48.png"))); // NOI18N
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });
        panelMusic.add(btnPlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 630, 60, -1));

        btnDetails.setBackground(new java.awt.Color(29, 38, 49));
        btnDetails.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/detailsMusic.png"))); // NOI18N
        btnDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailsActionPerformed(evt);
            }
        });
        panelMusic.add(btnDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 639, 40, 40));

        btnTim.setBackground(new java.awt.Color(29, 38, 49));
        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/heart24null.png"))); // NOI18N
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });
        panelMusic.add(btnTim, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 540, 40, 40));

        btnVolumn.setBackground(new java.awt.Color(29, 38, 49));
        btnVolumn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/volume24.png"))); // NOI18N
        panelMusic.add(btnVolumn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 40, 40));

        btnListMusic.setBackground(new java.awt.Color(29, 38, 49));
        btnListMusic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/play.png"))); // NOI18N
        panelMusic.add(btnListMusic, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 540, 40, 40));

        btnAddPlaylist.setBackground(new java.awt.Color(29, 38, 49));
        btnAddPlaylist.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add24.png"))); // NOI18N
        panelMusic.add(btnAddPlaylist, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 540, 40, 40));

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelMusic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addComponent(move, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(move, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelMusic, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lbOutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbOutMouseExited
        lbOut.setForeground(new Color(255, 0, 0));
    }//GEN-LAST:event_lbOutMouseExited

    private void lbOutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbOutMouseEntered
        lbOut.setForeground(new Color(153, 0, 0));
    }//GEN-LAST:event_lbOutMouseEntered

    private void lbOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbOutMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lbOutMouseClicked
    private int x;
    private int y;

    private void moveMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moveMouseMoved
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_moveMouseMoved

    private void moveMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moveMouseDragged
        setLocation(evt.getXOnScreen() - x, evt.getYOnScreen() - y);
    }//GEN-LAST:event_moveMouseDragged

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        pause();
    }//GEN-LAST:event_btnPlayActionPerformed

    private void btnAgainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgainActionPerformed
        playAgain();
    }//GEN-LAST:event_btnAgainActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        try {
            playPreviousSong();
        } catch (CannotReadException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TagException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ReadOnlyFileException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAudioFrameException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        try {
            playNextSong();
        } catch (CannotReadException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TagException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ReadOnlyFileException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAudioFrameException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        tim();
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailsActionPerformed
        FormDetailMusic details = new FormDetailMusic();
        setPanel(details);
    }//GEN-LAST:event_btnDetailsActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.Account account1;
    private javax.swing.JPanel body;
    private swing.Button btnAddPlaylist;
    private swing.Button btnAgain;
    private swing.Button btnDetails;
    private swing.Button btnDownload;
    private swing.Button btnListMusic;
    private swing.Button btnNext;
    private swing.Button btnPlay;
    private swing.Button btnPrev;
    private swing.Button btnTim;
    private swing.Button btnVolumn;
    private javax.swing.JScrollPane content;
    private form.FormTrangChu formTrangChu1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbName2;
    private javax.swing.JLabel lbOut;
    private javax.swing.JLabel lbStart;
    private javax.swing.JLabel lbTime;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbanh;
    private Menu.Menu menu1;
    private component.header move;
    private swing.Panel panel1;
    private swing.PanelBorder panelBorder1;
    private javax.swing.JPanel panelMusic;
    private swing.Slider slider;
    // End of variables declaration//GEN-END:variables
}
