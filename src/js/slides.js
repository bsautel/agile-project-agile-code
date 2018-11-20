import Reveal from 'reveal.js';
import 'reveal.js/lib/js/head.min';

import '../../node_modules/reveal.js/css/reveal.css'
import '../../node_modules/reveal.js/css/theme/moon.css'

Reveal.initialize({
    dependencies: [
        {src: 'plugin/notes/notes.js', async: true}
    ]
});
