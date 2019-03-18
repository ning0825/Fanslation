package com.tanhuan.fanslation.bean;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ParaBean {

    /**
     * collins : {"collins_entries":[{"basic_entries":{"basic_entry":[{"cet":"CET4 TEM4","headword":"apple","wordforms":{}}]},"entries":{"entry":[{"tran_entry":[{"exam_sents":{"sent":[{"chn_sent":"我要一个苹果。","eng_sent":"I want an apple."},{"chn_sent":"\u2026他对最佳品种苹果的不断寻找。","eng_sent":"...his ongoing search for the finest varieties of apple."}]},"pos_entry":{"pos":"N-VAR","pos_tips":"有变体名词"},"tran":"An <b>apple<\/b> is a round fruit with smooth red, yellow, or green skin and firm white flesh. 苹果"}]}]},"headword":"apple","phonetic":"ˈæpəl","star":"3"}]}
     * input : apple
     * lang : eng
     * le : en
     * meta : {"dicts":["collins","meta","simple"],"guessLanguage":"eng","input":"apple","lang":"eng","le":"en"}
     * simple : {"query":"apple","word":[{"return-phrase":"apple","ukphone":"'æp(ə)l","ukspeech":"apple&type=1","usphone":"'æpl","usspeech":"apple&type=2"}]}
     */

    private CollinsBean collins;
    private String input;
    private String lang;
    private String le;
    private MetaBean meta;
    private SimpleBean simple;

    public CollinsBean getCollins() {
        return collins;
    }

    public void setCollins(CollinsBean collins) {
        this.collins = collins;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLe() {
        return le;
    }

    public void setLe(String le) {
        this.le = le;
    }

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public SimpleBean getSimple() {
        return simple;
    }

    public void setSimple(SimpleBean simple) {
        this.simple = simple;
    }

    public static class CollinsBean {
        private List<CollinsEntriesBean> collins_entries;

        public List<CollinsEntriesBean> getCollins_entries() {
            return collins_entries;
        }

        public void setCollins_entries(List<CollinsEntriesBean> collins_entries) {
            this.collins_entries = collins_entries;
        }

        public static class CollinsEntriesBean {
            /**
             * basic_entries : {"basic_entry":[{"cet":"CET4 TEM4","headword":"apple","wordforms":{}}]}
             * entries : {"entry":[{"tran_entry":[{"exam_sents":{"sent":[{"chn_sent":"我要一个苹果。","eng_sent":"I want an apple."},{"chn_sent":"\u2026他对最佳品种苹果的不断寻找。","eng_sent":"...his ongoing search for the finest varieties of apple."}]},"pos_entry":{"pos":"N-VAR","pos_tips":"有变体名词"},"tran":"An <b>apple<\/b> is a round fruit with smooth red, yellow, or green skin and firm white flesh. 苹果"}]}]}
             * headword : apple
             * phonetic : ˈæpəl
             * star : 3
             */

            private BasicEntriesBean basic_entries;
            private EntriesBean entries;
            private String headword;
            private String phonetic;
            private String star;

            public BasicEntriesBean getBasic_entries() {
                return basic_entries;
            }

            public void setBasic_entries(BasicEntriesBean basic_entries) {
                this.basic_entries = basic_entries;
            }

            public EntriesBean getEntries() {
                return entries;
            }

            public void setEntries(EntriesBean entries) {
                this.entries = entries;
            }

            public String getHeadword() {
                return headword;
            }

            public void setHeadword(String headword) {
                this.headword = headword;
            }

            public String getPhonetic() {
                return phonetic;
            }

            public void setPhonetic(String phonetic) {
                this.phonetic = phonetic;
            }

            public String getStar() {
                return star;
            }

            public void setStar(String star) {
                this.star = star;
            }

            public static class BasicEntriesBean {
                private List<BasicEntryBean> basic_entry;

                public List<BasicEntryBean> getBasic_entry() {
                    return basic_entry;
                }

                public void setBasic_entry(List<BasicEntryBean> basic_entry) {
                    this.basic_entry = basic_entry;
                }

                public static class BasicEntryBean {
                    /**
                     * cet : CET4 TEM4
                     * headword : apple
                     * wordforms : {}
                     */

                    private String cet;
                    private String headword;
                    private WordformsBean wordforms;

                    public String getCet() {
                        return cet;
                    }

                    public void setCet(String cet) {
                        this.cet = cet;
                    }

                    public String getHeadword() {
                        return headword;
                    }

                    public void setHeadword(String headword) {
                        this.headword = headword;
                    }

                    public WordformsBean getWordforms() {
                        return wordforms;
                    }

                    public void setWordforms(WordformsBean wordforms) {
                        this.wordforms = wordforms;
                    }

                    public static class WordformsBean {
                    }
                }
            }

            public static class EntriesBean {
                private List<EntryBean> entry;

                public List<EntryBean> getEntry() {
                    return entry;
                }

                public void setEntry(List<EntryBean> entry) {
                    this.entry = entry;
                }

                public static class EntryBean {
                    private List<TranEntryBean> tran_entry;

                    public List<TranEntryBean> getTran_entry() {
                        return tran_entry;
                    }

                    public void setTran_entry(List<TranEntryBean> tran_entry) {
                        this.tran_entry = tran_entry;
                    }

                    public static class TranEntryBean {
                        /**
                         * exam_sents : {"sent":[{"chn_sent":"我要一个苹果。","eng_sent":"I want an apple."},{"chn_sent":"\u2026他对最佳品种苹果的不断寻找。","eng_sent":"...his ongoing search for the finest varieties of apple."}]}
                         * pos_entry : {"pos":"N-VAR","pos_tips":"有变体名词"}
                         * tran : An <b>apple</b> is a round fruit with smooth red, yellow, or green skin and firm white flesh. 苹果
                         */

                        private ExamSentsBean exam_sents;
                        private PosEntryBean pos_entry;
                        private String tran;

                        public ExamSentsBean getExam_sents() {
                            return exam_sents;
                        }

                        public void setExam_sents(ExamSentsBean exam_sents) {
                            this.exam_sents = exam_sents;
                        }

                        public PosEntryBean getPos_entry() {
                            return pos_entry;
                        }

                        public void setPos_entry(PosEntryBean pos_entry) {
                            this.pos_entry = pos_entry;
                        }

                        public String getTran() {
                            return tran;
                        }

                        public void setTran(String tran) {
                            this.tran = tran;
                        }

                        public static class ExamSentsBean {
                            private List<SentBean> sent;

                            public List<SentBean> getSent() {
                                return sent;
                            }

                            public void setSent(List<SentBean> sent) {
                                this.sent = sent;
                            }

                            public static class SentBean {
                                /**
                                 * chn_sent : 我要一个苹果。
                                 * eng_sent : I want an apple.
                                 */

                                private String chn_sent;
                                private String eng_sent;

                                public String getChn_sent() {
                                    return chn_sent;
                                }

                                public void setChn_sent(String chn_sent) {
                                    this.chn_sent = chn_sent;
                                }

                                public String getEng_sent() {
                                    return eng_sent;
                                }

                                public void setEng_sent(String eng_sent) {
                                    this.eng_sent = eng_sent;
                                }
                            }
                        }

                        public static class PosEntryBean {
                            /**
                             * pos : N-VAR
                             * pos_tips : 有变体名词
                             */

                            private String pos;
                            private String pos_tips;

                            public String getPos() {
                                return pos;
                            }

                            public void setPos(String pos) {
                                this.pos = pos;
                            }

                            public String getPos_tips() {
                                return pos_tips;
                            }

                            public void setPos_tips(String pos_tips) {
                                this.pos_tips = pos_tips;
                            }
                        }
                    }
                }
            }
        }
    }

    public static class MetaBean {
        /**
         * dicts : ["collins","meta","simple"]
         * guessLanguage : eng
         * input : apple
         * lang : eng
         * le : en
         */

        private String guessLanguage;
        private String input;
        private String lang;
        private String le;
        private List<String> dicts;

        public String getGuessLanguage() {
            return guessLanguage;
        }

        public void setGuessLanguage(String guessLanguage) {
            this.guessLanguage = guessLanguage;
        }

        public String getInput() {
            return input;
        }

        public void setInput(String input) {
            this.input = input;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public String getLe() {
            return le;
        }

        public void setLe(String le) {
            this.le = le;
        }

        public List<String> getDicts() {
            return dicts;
        }

        public void setDicts(List<String> dicts) {
            this.dicts = dicts;
        }
    }

    public static class SimpleBean {
        /**
         * query : apple
         * word : [{"return-phrase":"apple","ukphone":"'æp(ə)l","ukspeech":"apple&type=1","usphone":"'æpl","usspeech":"apple&type=2"}]
         */

        private String query;
        private List<WordBean> word;

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public List<WordBean> getWord() {
            return word;
        }

        public void setWord(List<WordBean> word) {
            this.word = word;
        }

        public static class WordBean {
            /**
             * return-phrase : apple
             * ukphone : 'æp(ə)l
             * ukspeech : apple&type=1
             * usphone : 'æpl
             * usspeech : apple&type=2
             */

            @SerializedName("return-phrase")
            private String returnphrase;
            private String ukphone;
            private String ukspeech;
            private String usphone;
            private String usspeech;

            public String getReturnphrase() {
                return returnphrase;
            }

            public void setReturnphrase(String returnphrase) {
                this.returnphrase = returnphrase;
            }

            public String getUkphone() {
                return ukphone;
            }

            public void setUkphone(String ukphone) {
                this.ukphone = ukphone;
            }

            public String getUkspeech() {
                return ukspeech;
            }

            public void setUkspeech(String ukspeech) {
                this.ukspeech = ukspeech;
            }

            public String getUsphone() {
                return usphone;
            }

            public void setUsphone(String usphone) {
                this.usphone = usphone;
            }

            public String getUsspeech() {
                return usspeech;
            }

            public void setUsspeech(String usspeech) {
                this.usspeech = usspeech;
            }
        }
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        List<CollinsBean.CollinsEntriesBean.EntriesBean.EntryBean> entryBeans
                = getCollins().getCollins_entries().get(0).getEntries().entry;
        for (CollinsBean.CollinsEntriesBean.EntriesBean.EntryBean entryBean : entryBeans) {
            CollinsBean.CollinsEntriesBean.EntriesBean.EntryBean.TranEntryBean tranEntryBean
                    = entryBean.getTran_entry().get(0);
            s = s.append(tranEntryBean.tran + "\n");
            if (tranEntryBean.exam_sents != null) {
                List<CollinsBean.CollinsEntriesBean.EntriesBean.EntryBean.TranEntryBean.ExamSentsBean.SentBean> sentBeans
                        = tranEntryBean.exam_sents.sent;
                if (sentBeans != null) {
                    for (CollinsBean.CollinsEntriesBean.EntriesBean.EntryBean.TranEntryBean.ExamSentsBean.SentBean sentBean : sentBeans) {
                        s.append(sentBean.eng_sent + "\n" + sentBean.chn_sent + "\n");
                    }
                }

            }


        }

        return s.toString();
    }
}
