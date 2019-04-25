package com.tanhuan.fanslation.bean;

import java.util.List;

public class IcibaBean {


    /**
     * word_name : tough
     * is_CRI : 1
     * exchange : {"word_pl":["toughs"],"word_third":["toughs"],"word_past":["toughed"],"word_done":["toughed"],"word_ing":["toughing"],"word_er":["tougher"],"word_est":["toughest"]}
     * symbols : [{"ph_en":"tʌf","ph_am":"tʌf","ph_other":"","ph_en_mp3":"http://res.iciba.com/resource/amp3/oxford/0/f7/0e/f70e799cb9c6d07ab6fbddd4dd79c861.mp3","ph_am_mp3":"http://res.iciba.com/resource/amp3/1/0/96/f5/96f5c06fee4b02dd690d1f41687a31e2.mp3","ph_tts_mp3":"http://res-tts.iciba.com/9/6/f/96f5c06fee4b02dd690d1f41687a31e2.mp3","parts":[{"part":"adj.","means":["坚强的，坚韧的，不屈不挠的","艰苦的，困难的，难办的","牢固的，强壮的","粗暴的"]},{"part":"n.","means":["粗暴的人","暴徒，恶棍"]},{"part":"vt.","means":["<口>忍耐，忍受（常与out连用）"]},{"part":"adv.","means":["强硬地，顽强地","以强硬的态度"]}]}]
     */

    private String word_name;
    private String is_CRI;
    private ExchangeBean exchange;
    private List<SymbolsBean> symbols;

    public String getWord_name() {
        return word_name;
    }

    public void setWord_name(String word_name) {
        this.word_name = word_name;
    }

    public String getIs_CRI() {
        return is_CRI;
    }

    public void setIs_CRI(String is_CRI) {
        this.is_CRI = is_CRI;
    }

    public ExchangeBean getExchange() {
        return exchange;
    }

    public void setExchange(ExchangeBean exchange) {
        this.exchange = exchange;
    }

    public List<SymbolsBean> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<SymbolsBean> symbols) {
        this.symbols = symbols;
    }

    public static class ExchangeBean {
    }

    public static class SymbolsBean {
        /**
         * ph_en : tʌf
         * ph_am : tʌf
         * ph_other :
         * ph_en_mp3 : http://res.iciba.com/resource/amp3/oxford/0/f7/0e/f70e799cb9c6d07ab6fbddd4dd79c861.mp3
         * ph_am_mp3 : http://res.iciba.com/resource/amp3/1/0/96/f5/96f5c06fee4b02dd690d1f41687a31e2.mp3
         * ph_tts_mp3 : http://res-tts.iciba.com/9/6/f/96f5c06fee4b02dd690d1f41687a31e2.mp3
         * parts : [{"part":"adj.","means":["坚强的，坚韧的，不屈不挠的","艰苦的，困难的，难办的","牢固的，强壮的","粗暴的"]},{"part":"n.","means":["粗暴的人","暴徒，恶棍"]},{"part":"vt.","means":["<口>忍耐，忍受（常与out连用）"]},{"part":"adv.","means":["强硬地，顽强地","以强硬的态度"]}]
         */

        private String ph_en;
        private String ph_am;
        private String ph_other;
        private String ph_en_mp3;
        private String ph_am_mp3;
        private String ph_tts_mp3;
        private List<PartsBean> parts;

        public String getPh_en() {
            return ph_en;
        }

        public void setPh_en(String ph_en) {
            this.ph_en = ph_en;
        }

        public String getPh_am() {
            return ph_am;
        }

        public void setPh_am(String ph_am) {
            this.ph_am = ph_am;
        }

        public String getPh_other() {
            return ph_other;
        }

        public void setPh_other(String ph_other) {
            this.ph_other = ph_other;
        }

        public String getPh_en_mp3() {
            return ph_en_mp3;
        }

        public void setPh_en_mp3(String ph_en_mp3) {
            this.ph_en_mp3 = ph_en_mp3;
        }

        public String getPh_am_mp3() {
            return ph_am_mp3;
        }

        public void setPh_am_mp3(String ph_am_mp3) {
            this.ph_am_mp3 = ph_am_mp3;
        }

        public String getPh_tts_mp3() {
            return ph_tts_mp3;
        }

        public void setPh_tts_mp3(String ph_tts_mp3) {
            this.ph_tts_mp3 = ph_tts_mp3;
        }

        public List<PartsBean> getParts() {
            return parts;
        }

        public void setParts(List<PartsBean> parts) {
            this.parts = parts;
        }

        public static class PartsBean {
            /**
             * part : adj.
             * means : ["坚强的，坚韧的，不屈不挠的","艰苦的，困难的，难办的","牢固的，强壮的","粗暴的"]
             */

            private String part;
            private List<String> means;

            public String getPart() {
                return part;
            }

            public void setPart(String part) {
                this.part = part;
            }

            public List<String> getMeans() {
                return means;
            }

            public void setMeans(List<String> means) {
                this.means = means;
            }
        }
    }
}
