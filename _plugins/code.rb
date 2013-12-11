module Jekyll

    require 'coderay'

    class CodeBlock < Liquid::Block

        def initialize(tag_name, lang, tokens)
            @lang = lang.strip.downcase.to_sym
            super
        end

        def render(context)
            "#{CodeRay.scan(super.join.strip, @lang).div(:css => :class)}"
        end
    
    end

end

Liquid::Template.register_tag('code', Jekyll::CodeBlock)
