package com.gangulwar.peekip;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/getInfo.svg")
public class SvgController {

    @GetMapping(produces = "image/svg+xml")
    public String getInfoSvg(HttpServletRequest request) {
        return generateSvg(SimpleClass.req(request), request);
    }

    private String generateSvg(String text, HttpServletRequest request) {
        String contextPath = request.getContextPath();
        return
                "<svg xmlns=\"http://www.w3.org/2000/svg\">" +
                        "<defs>" +
                        "<style> text { font-size: 12px; } </style>" +
                        "<script type=\"text/ecmascript\"><![CDATA[" +
                        "function updateSize() {" +
                        "var svg = document.querySelector('svg');" +
                        "var text = document.querySelector('text');" +
                        "var bbox = text.getBBox();" +
                        "var padding = 10;" +
                        "var width = bbox.width + padding * 2;" +
                        "var height = bbox.height + padding * 2;" +
                        "svg.setAttribute('width', width);" +
                        "svg.setAttribute('height', height);" +
                        "} window.onload = updateSize; ]]>" +
                        "</script>" +
                        "</defs>" +
                        "<rect width=\"100%\" height=\"100%\" fill=\"black\" />" +
                        "<text x=\"50%\" y=\"50%\" dominant-baseline=\"middle\" text-anchor=\"middle\" fill=\"white\">" +
                        "%s" + text + "</text></svg>";
    }
}
