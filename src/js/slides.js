import Reveal from 'reveal.js';
import 'reveal.js/lib/js/head.min';

import '../../node_modules/reveal.js/css/reveal.css'
import '../../node_modules/reveal.js/css/theme/moon.css'
import '../../node_modules/reveal.js/lib/css/zenburn.css'
import '../css/slides.scss';

Reveal.initialize({
    dependencies: [
        {
            src: 'plugin/notes/notes.js',
            async: true
        },
        {
            src: 'plugin/highlight/highlight.js',
            async: true,
            callback() {
                window.hljs.initHighlightingOnLoad();
            }
        },
    ],
    history: true,
    controls: false,
    progress: false,
    slideNumber: true
});
